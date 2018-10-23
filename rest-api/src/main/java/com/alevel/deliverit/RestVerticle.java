package com.alevel.deliverit;

import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.customers.verticle.VertxContext;
import com.alevel.deliverit.gateway.ByteArrayCodec;
import com.alevel.deliverit.gateway.LogisticsVerticle;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;


/**
 * Receipt request {@link ParcelReceptionEndpoint#accept(String)}.
 * Give back json response
 *
 * @author Vitalii Usatyi
 */
public class RestVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        VertxOptions vOpts = new VertxOptions();
        vOpts.setClustered(true).setBlockedThreadCheckInterval(1000 * 60 * 60);
        ;
        Vertx.clusteredVertx(vOpts, cluster -> {
            if (cluster.succeeded()) {
                final Vertx result = cluster.result();
                result.deployVerticle(new RestVerticle());
                result.deployVerticle(new LogisticsVerticle());
            }
        });
    }

    @Override
    public void start() throws Exception {
        VertxContext.instance().setEventBus(vertx.eventBus());
        vertx.eventBus().registerCodec(new ByteArrayCodec());
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(request -> {
            Buffer fullRequestBody = Buffer.buffer();
            if (request.method() == HttpMethod.POST) {
                request.bodyHandler(buffer -> {
                    fullRequestBody.appendBuffer(buffer);
                    String jsonRequest = buffer.toString();
                    System.out.println(jsonRequest.length());

                    new ParcelReceptionEndpoint().accept(jsonRequest, parcelReceipt -> {
                        String jsonResponse = new Gson().toJson(parcelReceipt);
                        HttpServerResponse response = request.response();
                        response.putHeader("content-type", "application/json; charset=utf-8");
                        response.end(jsonResponse);
                    });
                });
            }
        }).listen(8080);
    }
}
