package com.alevel.deliverit.customers.verticle;

import com.alevel.deliverit.codecs.DefaultCodec;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

/**
 * @author Eugene Mitrofanov
 * @author Sergey Bogovesov
 */

public class VertxContext {
    private EventBus eb;
    private DeliveryOptions options;

    private VertxContext() {
        DefaultCodec defaultCodec = new DefaultCodec();
        options = new DeliveryOptions().setCodecName(defaultCodec.name());
    }

    public EventBus eventBus() {
        return eb;
    }

    public DeliveryOptions getOptions() {
        return options;
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
