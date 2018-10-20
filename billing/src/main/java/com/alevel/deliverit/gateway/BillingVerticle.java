package com.alevel.deliverit.gateway;

import com.alevel.deliverit.DeliveryVerticleContext;
import com.alevel.deliverit.ModuleAPI;
import com.alevel.deliverit.ServiceMethod;
import com.alevel.deliverit.billing.MoneyCodec;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

import java.util.Map;
import java.util.Optional;

/**
 * @author Sergey Bogovesov
 */
public class BillingVerticle extends AbstractVerticle {
    private MoneyCodec moneyCodec = new MoneyCodec();

    public BillingVerticle(String address) {
        ModuleAPI.getInstance().registerConsumers(new PriceLookup());
        DeliveryOptions options = new DeliveryOptions().setCodecName(moneyCodec.name());
        DeliveryVerticleContext.getInstance().add(address, options);
    }

    @Override
    public void start() throws Exception {
        Map<String, ServiceMethod> consumersContainer = ModuleAPI.getInstance().getConsumersContainer();

        EventBus eb = vertx.eventBus();
        eb.registerCodec(moneyCodec);

        for (Map.Entry<String, ServiceMethod> e : consumersContainer.entrySet()) {

            String address = e.getKey();
            ServiceMethod value = e.getValue();

            eb.consumer(address, message -> {
                Optional optional = value.invokeConsumer(message.body());
                if (optional.isPresent() && !optional.equals(Optional.empty())) {
                    final Optional<DeliveryOptions> options = DeliveryVerticleContext.getInstance().get(address);
                    if (optional.isPresent()) {
                        message.reply(optional.get(), options.get());
                    } else {
                        throw new IllegalArgumentException("DeliveryOptions is not valid");
                    }
                }
            });
        }
    }
}
