package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.postal.network.Route;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

import java.util.concurrent.CountDownLatch;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsGateway {

    public static Route find(RouteLookupRequest request) {
        RouteLookupCodec routeLookupCodec = new RouteLookupCodec();
        VertxContext.instance().eventBus().registerCodec(routeLookupCodec);
        DeliveryOptions options = new DeliveryOptions().setCodecName(routeLookupCodec.name());

        Future<Route> route = Future.future();
        final CountDownLatch latch = new CountDownLatch(1);
        VertxContext.instance().eventBus().send("logistics.calculate.distance", request, options, reply -> {
            if (reply.succeeded()) {
                route.complete((Route) reply.result().body());
            } else {
                throw new IllegalStateException("LogisticsGateway Error");
            }
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return route.result();
    }
}
