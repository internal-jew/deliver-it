package com.alevel.deliverit;

import com.alevel.deliverit.codecs.DefaultCodec;
import com.alevel.deliverit.customers.verticle.CustomersVerticle;
import com.alevel.deliverit.gateway.*;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Main {

    static {
        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(vertxOptions);
        vertx.eventBus().registerCodec(new DefaultCodec());

        ModuleAPI.getInstance().registerConsumers(new RouteLookup());
        ModuleAPI.getInstance().registerConsumers(new PriceLookup());
        ModuleAPI.getInstance().registerConsumers(new DeliveryTimeLookup());
        ModuleAPI.getInstance().registerConsumers(new TrackNumberLookup());

        vertx.deployVerticle(new LogisticsVerticle());
        vertx.deployVerticle(new CustomersVerticle());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new RestVerticle().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
