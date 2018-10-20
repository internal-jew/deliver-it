package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.verticle.CustomersVerticle;
import com.alevel.deliverit.gateway.BillingVerticle;
import com.alevel.deliverit.gateway.LogisticsVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.alevel.deliverit.SubscribeAddress.BILLING_CALCULATE_PRICE;
import static com.alevel.deliverit.SubscribeAddress.LOGISTICS_CALCULATE_DISTANCE;
import static com.alevel.deliverit.customers.Given.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sergey Bogovesov
 */
@DisplayName("ParcelReception should")
class ParcelReceptionTest {

    @Test
    @DisplayName("Receiving a package from the sender")
    void accept() {
        Parcel parcel = givenParcel();
        Sender sender = givenSender();

        final ParcelReception packageReception = ParcelReception
                .builder()
                .setParcel(parcel)
                .setSender(sender)
                .setDeliveryTime(getDeliveryTime())
                .setEstimatedPriceCalculator(getEstimatedPriceCalculator())
                .setTrackNumbers(getTrackNumbers())
                .build();


        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(vertxOptions);
        vertx.deployVerticle(new LogisticsVerticle(LOGISTICS_CALCULATE_DISTANCE));
        vertx.deployVerticle(new BillingVerticle(BILLING_CALCULATE_PRICE));
        vertx.deployVerticle(new CustomersVerticle());

        ParcelReceipt parcelReceipt = packageReception.accept();

        LocalDate estimatedDeliveryTime = parcelReceipt.getDeliveryTime().getEstimatedDeliveryTime();

        long price = parcelReceipt.getPrice().getValue();

        assertEquals(parcelReceipt.getParcel(), parcel);
        assertEquals(LocalDate.now().plusDays(10), estimatedDeliveryTime);
        assertEquals(545012L, price);
    }
}
