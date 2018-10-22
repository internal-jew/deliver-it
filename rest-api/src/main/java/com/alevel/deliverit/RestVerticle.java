package com.alevel.deliverit;

import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.customers.verticle.VertxContext;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

import java.io.*;


/**
 * Receipt request {@link ParcelReceptionEndpoint#accept(String)}.
 * Give back json response
 *
 * @author Vitalii Usatyi
 */
public class RestVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        VertxContext.instance().setEventBus(vertx.eventBus());
        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        vertx.eventBus().registerCodec(new MessageCodec() {
            @Override
            public void encodeToWire(Buffer buffer, Object o) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
                    out.writeObject(o);
                    out.flush();
                    final byte[] bytes = bos.toByteArray();
                    buffer.appendBytes(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Object decodeFromWire(int pos, Buffer buffer) {
                ByteArrayInputStream bis = new ByteArrayInputStream(buffer.getBytes(pos, buffer.length()));
                ObjectInput in = null;
                try {
                    in = new ObjectInputStream(bis);
                    Object o = in.readObject();
                    return o;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    return null;
                }
            }

            @Override
            public Object transform(Object o) {
                return o;
            }

            @Override
            public String name() {
                return "myCodec";
            }

            @Override
            public byte systemCodecID() {
                return -1;
            }
        });
        Vertx vertx = Vertx.vertx(vertxOptions);

//        vertx.eventBus().registerCodec(new DefaultCodec());
//        init();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(request -> {
            Buffer fullRequestBody = Buffer.buffer();
            if (request.method() == HttpMethod.POST) {
                request.bodyHandler(buffer -> {
                    fullRequestBody.appendBuffer(buffer);
                    String jsonRequest = buffer.toString();
                    System.out.println(jsonRequest.length());

                    ParcelReceipt parcelReceipt = new ParcelReceptionEndpoint().accept(jsonRequest);
                    String jsonResponse = new Gson().toJson(parcelReceipt);
                    HttpServerResponse response = request.response();
                    response.putHeader("content-type", "application/json; charset=utf-8");
                    response.end(jsonResponse);
                });
            }
        }).listen(8080);
    }
}
