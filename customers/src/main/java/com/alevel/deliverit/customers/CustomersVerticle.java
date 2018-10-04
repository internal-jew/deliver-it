package com.alevel.deliverit.customers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @author Eugene Mitrofanov
 */



public class CustomersVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Vertx vertx = Vertx.vertx();
        VertxContext.instance().setEventBus(vertx.eventBus());
    }
}
