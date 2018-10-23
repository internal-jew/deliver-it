package com.alevel.deliverit.gateway;

import com.alevel.deliverit.ModuleAPI;
import com.alevel.deliverit.ServiceMethod;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

import java.util.Map;
import java.util.Optional;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsVerticle extends AbstractVerticle {
    private DeliveryOptions options;

    public LogisticsVerticle() {
        init();
        options = new DeliveryOptions().setCodecName(ByteArrayCodec.CODEC_NAME);
    }

    @Override
    public void start() {
        Map<String, ServiceMethod> consumersContainer = ModuleAPI.getInstance().getConsumersContainer();

        EventBus eb = getVertx().eventBus();

        for (Map.Entry<String, ServiceMethod> e : consumersContainer.entrySet()) {

            String address = e.getKey();
            ServiceMethod value = e.getValue();

            eb.consumer(address, message -> {
                Optional optional = value.invokeConsumer(message.body());
                if (optional.isPresent() && !optional.equals(Optional.empty())) {
                    System.out.println("Sending reply");
                    message.reply(optional.get(), options);
                    System.out.println("Reply sent");
                }
            });
        }
    }

    private void init() {
        ModuleAPI.getInstance().registerConsumers(new RouteLookup());
        ModuleAPI.getInstance().registerConsumers(new PriceLookup());
        ModuleAPI.getInstance().registerConsumers(new DeliveryTimeLookup());
        ModuleAPI.getInstance().registerConsumers(new TrackNumberLookup());
    }
}
