package com.alevel.deliverit.customers.gateway;

import com.alevel.deliverit.customers.request.RouteLookupCodec;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.customers.verticle.VertxContext;
import com.alevel.deliverit.logistics.postal.network.Route;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

import java.util.concurrent.CountDownLatch;

import static com.alevel.deliverit.SubscribeAddress.LOGISTICS_CALCULATE_DISTANCE;

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
        VertxContext.instance().eventBus().send(LOGISTICS_CALCULATE_DISTANCE, request, options, reply -> {
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
