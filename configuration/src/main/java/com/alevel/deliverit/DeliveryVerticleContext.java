package com.alevel.deliverit;

import io.vertx.core.eventbus.DeliveryOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Sergey Bogovesov
 * For each address contains the parameters of the object that will be sent by event bus.
 */
public class DeliveryVerticleContext {
    private final Map<String, DeliveryOptions> deliveryOptions = new HashMap<>();

    private DeliveryVerticleContext() {
    }

    public void add(String address, DeliveryOptions options) {
        deliveryOptions.put(address, options);
    }

    public Optional<DeliveryOptions> get(String address) {
        return Optional.ofNullable(deliveryOptions.get(address));
    }

    public static DeliveryVerticleContext getInstance() {
        return Singleton.INSTANCE.instance;
    }

    private enum Singleton {
        INSTANCE;
        private final DeliveryVerticleContext instance = new DeliveryVerticleContext();
    }
}
