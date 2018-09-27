package com.alevel.deliverit;

import com.alevel.deliverit.customers.ParcelReceipt;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;


/**
 * Receipt HTTP POST request{@link ParcelReceptionEndpoint#accept(String)}.
 * Give back json response
 *
 * @author Vitalii Usatyi
 */
public class RestVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(request -> {
            Buffer fullRequestBody = Buffer.buffer();
            if (request.method() == HttpMethod.POST) {
                request.handler(buffer -> {
                    fullRequestBody.appendBuffer(buffer);
                    String jsonRequest = buffer.toString();
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
