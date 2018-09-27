package com.alevel.deliverit;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Receipt HTTP POST request and call EndPoint.start(...) .
 *
 * @author Vitalii Usatyi
 */
public class RestVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router
                .route("/customers/acceptParcel")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "application/json; charset=utf-8");
                    response.end("Please do POST request");
                });

        httpServer.requestHandler(new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest request) {
                Buffer fullRequestBody = Buffer.buffer();
                if (request.method() == HttpMethod.POST) {
                    System.out.println("Request !!!! ");
                    request.handler(new Handler<Buffer>() {
                        @Override
                        public void handle(Buffer buffer) {
                            fullRequestBody.appendBuffer(buffer);
                            String jsonRequest = buffer.toString();
                            new Endpoint().run(jsonRequest);
                        }
                    });
                    System.out.println(fullRequestBody.toString());
                }
            }
        }).listen(8080);
    }
}
