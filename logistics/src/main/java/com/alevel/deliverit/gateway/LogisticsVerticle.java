package com.alevel.deliverit.gateway;

import com.alevel.deliverit.ModuleAPI;
import com.alevel.deliverit.ServiceMethod;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

import java.util.Map;
import java.util.Optional;

public class LogisticsVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        Vertx vertx = Vertx.vertx();

        ModuleAPI.getInstance().registerConsumers(new RouteLookup());

        Map<String, ServiceMethod> consumersContainer = ModuleAPI.getInstance().getConsumersContainer();
        EventBus eb =  vertx.eventBus();

        for (Map.Entry<String, ServiceMethod> e : consumersContainer.entrySet()) {
            String address = e.getKey();
            ServiceMethod value = e.getValue();
            eb.consumer(address, message -> {
                Optional optional = value.invokeConsumer(message.body());
                if(optional.isPresent()&&!optional.equals(Optional.empty())){
                    message.reply(optional.get());
                }
            });
        }
    }
}
