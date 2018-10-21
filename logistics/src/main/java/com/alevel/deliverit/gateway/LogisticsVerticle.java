package com.alevel.deliverit.gateway;

import com.alevel.deliverit.ModuleAPI;
import com.alevel.deliverit.ServiceMethod;
import com.alevel.deliverit.codecs.DefaultCodec;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

import java.util.Map;
import java.util.Optional;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsVerticle extends AbstractVerticle {
    private DefaultCodec defaultCodec = new DefaultCodec();
    private DeliveryOptions options;

    public LogisticsVerticle() {
        options = new DeliveryOptions().setCodecName(defaultCodec.name());
    }

    @Override
    public void start() throws Exception {
        Map<String, ServiceMethod> consumersContainer = ModuleAPI.getInstance().getConsumersContainer();

        EventBus eb = vertx.eventBus();

        for (Map.Entry<String, ServiceMethod> e : consumersContainer.entrySet()) {

            String address = e.getKey();
            ServiceMethod value = e.getValue();

            eb.consumer(address, message -> {
                Optional optional = value.invokeConsumer(message.body());
                if (optional.isPresent() && !optional.equals(Optional.empty())) {
                    message.reply(optional.get(), options);
                }
            });
        }
    }
}
