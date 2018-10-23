package com.alevel.deliverit.customers.gateway;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.customers.verticle.VertxContext;
import com.alevel.deliverit.gateway.ByteArrayCodec;
import com.alevel.deliverit.logistics.DeliveryTimeRequest;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberRequest;
import com.alevel.deliverit.logistics.postal.network.Route;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

import java.util.function.Consumer;

import static com.alevel.deliverit.SubscribeAddress.*;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsGateway {

    private static <T, R> T send(R request, String address, Consumer<T> callback) {
        DeliveryOptions options = VertxContext.instance().getOptions().setCodecName(ByteArrayCodec.CODEC_NAME);

        Future<T> route = Future.future();
        System.out.println("Sending request " + address);
        VertxContext.instance().eventBus().send(address, request, options, reply -> {
            System.out.println("Response came");
            if (reply.succeeded()) {
                System.out.println("Response succeed");
                callback.accept((T) reply.result().body());
            } else {
                reply.cause().printStackTrace();
                throw new IllegalStateException("LogisticsGateway " + address + " error");
            }
        });
        return route.result();
    }

    public static Route find(RouteLookupRequest request, Consumer<Route> callback) {
        return send(request, LOGISTICS_CALCULATE_DISTANCE, callback);
    }

    public static EstimatedDeliveryTime estimate(DeliveryTimeRequest request, Consumer<EstimatedDeliveryTime> callback) {
        return send(request, LOGISTICS_ESTIMATE_DELIVERY_TIME, callback);
    }

    public static TrackNumber registerParcel(TrackNumberRequest request, Consumer<TrackNumber> callback) {
        return send(request, LOGISTICS_TRACK_NUMBER_REGISTER_PARCEL, callback);
    }
}
