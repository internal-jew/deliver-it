package com.alevel.deliverit.customers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @author Eugene Mitrofanov
 */

public class CustomersVerticle extends AbstractVerticle {
    private final Vertx vertx;
    private final EventBus eventBus;

    public CustomersVerticle(Vertx vertx) {
        this.vertx = vertx;
        this.eventBus = vertx.eventBus();
    }

    @Override
    public void start() throws Exception {

        VertxContext.instance().setEventBus(eventBus);
    }
}
