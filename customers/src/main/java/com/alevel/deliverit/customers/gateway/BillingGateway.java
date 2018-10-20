package com.alevel.deliverit.customers.gateway;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.request.PriceLookupCodec;
import com.alevel.deliverit.customers.request.PriceLookupRequest;
import com.alevel.deliverit.customers.verticle.VertxContext;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;

import java.util.concurrent.CountDownLatch;

import static com.alevel.deliverit.SubscribeAddress.BILLING_CALCULATE_PRICE;

/**
 * @author Sergey Bogovesov
 */
public class BillingGateway {

    public static Money estimatedPrice(PriceLookupRequest request) {
        PriceLookupCodec priceLookupCodec = new PriceLookupCodec();
        VertxContext.instance().eventBus().registerCodec(priceLookupCodec);
        DeliveryOptions options = new DeliveryOptions().setCodecName(priceLookupCodec.name());

        Future<Money> money = Future.future();
        final CountDownLatch latch = new CountDownLatch(1);
        VertxContext.instance().eventBus().send(BILLING_CALCULATE_PRICE, request, options, reply -> {
            if (reply.succeeded()) {
                money.complete((Money) reply.result().body());
            } else {
                throw new IllegalStateException("BillingGateway Error");
            }
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return money.result();
    }
}
