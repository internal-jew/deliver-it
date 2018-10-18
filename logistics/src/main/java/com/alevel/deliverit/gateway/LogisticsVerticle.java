package com.alevel.deliverit.gateway;

import com.alevel.deliverit.ModuleAPI;
import com.alevel.deliverit.ServiceMethod;
import com.alevel.deliverit.logistics.postal.network.RouteCodec;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

import java.util.Map;
import java.util.Optional;

public class LogisticsVerticle extends AbstractVerticle {
    EventBus eventBus;

    public LogisticsVerticle(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void start() throws Exception {
//        Vertx vertx = Vertx.vertx();

        ModuleAPI.getInstance().registerConsumers(new RouteLookup());

        Map<String, ServiceMethod> consumersContainer = ModuleAPI.getInstance().getConsumersContainer();
//        EventBus eb =  vertx.eventBus();

        RouteCodec routeCodec = new RouteCodec();
        eventBus.registerCodec(routeCodec);
        DeliveryOptions options = new DeliveryOptions().setCodecName(routeCodec.name());

        for (Map.Entry<String, ServiceMethod> e : consumersContainer.entrySet()) {
            String address = e.getKey();
            ServiceMethod value = e.getValue();
            eventBus.consumer(address, message -> {
                Optional optional = value.invokeConsumer(message.body());
                if (optional.isPresent() && !optional.equals(Optional.empty())) {
                    message.reply(optional.get(), options);
                }
            });
        }
    }
}
