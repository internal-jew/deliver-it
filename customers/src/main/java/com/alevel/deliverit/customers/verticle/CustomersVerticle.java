package com.alevel.deliverit.customers.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

/**
 * @author Eugene Mitrofanov
 */

public class CustomersVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        VertxContext.instance().setEventBus(vertx.eventBus());
    }

    public EventBus getEventsBus() {
        return VertxContext.instance().eventBus();
    }
}
