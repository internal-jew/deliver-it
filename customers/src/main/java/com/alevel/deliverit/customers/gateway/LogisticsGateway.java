package com.alevel.deliverit.customers.gateway;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.customers.verticle.VertxContext;
import com.alevel.deliverit.logistics.DeliveryTimeRequest;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberRequest;
import com.alevel.deliverit.logistics.postal.network.Route;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

import java.util.concurrent.CountDownLatch;

import static com.alevel.deliverit.SubscribeAddress.*;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsGateway {

    private static <T, R> T send(R request, String address) {
        DeliveryOptions options = VertxContext.instance().getOptions();

        Future<T> route = Future.future();
        final CountDownLatch latch = new CountDownLatch(1);

        VertxContext.instance().eventBus().send(address, request, options, reply -> {
            if (reply.succeeded()) {
                route.complete((T) reply.result().body());
            } else {
                throw new IllegalStateException("LogisticsGateway " + address + " error");
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

    public static Route find(RouteLookupRequest request) {
        return send(request, LOGISTICS_CALCULATE_DISTANCE);
    }

    public static EstimatedDeliveryTime estimate(DeliveryTimeRequest request) {
        return send(request, LOGISTICS_ESTIMATE_DELIVERY_TIME);
    }

    public static TrackNumber registerParcel(TrackNumberRequest request) {
        return send(request, LOGISTICS_TRACK_NUMBER_REGISTER_PARCEL);
    }
}
