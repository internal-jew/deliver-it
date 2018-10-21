package com.alevel.deliverit.customers.gateway;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.customers.verticle.VertxContext;
import com.alevel.deliverit.logistics.DeliveryTimeRequest;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.postal.network.Route;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

import java.util.concurrent.CountDownLatch;

import static com.alevel.deliverit.SubscribeAddress.LOGISTICS_CALCULATE_DISTANCE;
import static com.alevel.deliverit.SubscribeAddress.LOGISTICS_ESTIMATE_DELIVERY_TIME;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsGateway {

    public static Route find(RouteLookupRequest request) {
        DeliveryOptions options = VertxContext.instance().getOptions();

        Future<Route> route = Future.future();
        final CountDownLatch latch = new CountDownLatch(1);

        VertxContext.instance().eventBus().send(LOGISTICS_CALCULATE_DISTANCE, request, options, reply -> {
            if (reply.succeeded()) {
                route.complete((Route) reply.result().body());
            } else {
                throw new IllegalStateException("LogisticsGateway find route error");
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

    public static EstimatedDeliveryTime estimate(DeliveryTimeRequest request) {
        DeliveryOptions options = VertxContext.instance().getOptions();

        Future<EstimatedDeliveryTime> route = Future.future();
        final CountDownLatch latch = new CountDownLatch(1);

        VertxContext.instance().eventBus().send(LOGISTICS_ESTIMATE_DELIVERY_TIME, request, options, reply -> {
            if (reply.succeeded()) {
                route.complete((EstimatedDeliveryTime) reply.result().body());
            } else {
                throw new IllegalStateException("LogisticsGateway estimate delivery time error");
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
