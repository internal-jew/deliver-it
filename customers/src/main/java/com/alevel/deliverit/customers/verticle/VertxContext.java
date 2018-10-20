package com.alevel.deliverit.customers.verticle;

import io.vertx.core.eventbus.EventBus;

/**
 * @author Eugene Mitrofanov
 */

public class VertxContext {
    private EventBus eb;

    private VertxContext(){

    }

    public EventBus eventBus() {
        return eb;
    }

    public void setEventBus(EventBus eventBus) {
        this.eb = eventBus;
    }

    public static VertxContext instance() {
        return Singleton.INSTANCE.vertxContext;
    }

    private enum Singleton {
        INSTANCE;
        private final VertxContext vertxContext;

        Singleton() {
            vertxContext = new VertxContext();
        }
    }
}
