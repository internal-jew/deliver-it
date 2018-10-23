package com.alevel.deliverit.customers.gateway;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.request.PriceLookupRequest;
import com.alevel.deliverit.customers.verticle.VertxContext;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

import java.util.function.Consumer;

import static com.alevel.deliverit.SubscribeAddress.BILLING_CALCULATE_PRICE;

/**
 * @author Sergey Bogovesov
 */
public class BillingGateway {

    public static Money estimatedPrice(PriceLookupRequest request, Consumer<Money> callback) {
        DeliveryOptions options = VertxContext.instance().getOptions();

        Future<Money> money = Future.future();

        VertxContext.instance().eventBus().send(BILLING_CALCULATE_PRICE, request, options, reply -> {
            if (reply.succeeded()) {
                callback.accept((Money) reply.result().body());
//                money.complete((Money) reply.result().body());
            } else {
                throw new IllegalStateException("BillingGateway Error " + reply.cause());
            }
        });
        return money.result();
    }
}
