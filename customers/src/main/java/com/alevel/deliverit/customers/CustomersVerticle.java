package com.alevel.deliverit.customers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;

/**
 * @author Eugene Mitrofanov
 */

public class CustomersVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(vertxOptions);
        VertxContext.instance().setEventBus(vertx.eventBus());
    }

    public EventBus getEventsBus() {
        return VertxContext.instance().eventBus();
    }
}
