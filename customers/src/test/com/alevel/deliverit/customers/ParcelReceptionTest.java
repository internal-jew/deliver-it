package com.alevel.deliverit.customers;

import com.alevel.deliverit.ModuleAPI;
import com.alevel.deliverit.codecs.DefaultCodec;
import com.alevel.deliverit.customers.verticle.CustomersVerticle;
import com.alevel.deliverit.gateway.*;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.alevel.deliverit.customers.Given.givenParcel;
import static com.alevel.deliverit.customers.Given.givenSender;

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
                .build();


        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(vertxOptions);
        vertx.eventBus().registerCodec(new DefaultCodec());

        ModuleAPI.getInstance().registerConsumers(new RouteLookup());
        ModuleAPI.getInstance().registerConsumers(new PriceLookup());
        ModuleAPI.getInstance().registerConsumers(new DeliveryTimeLookup());
        ModuleAPI.getInstance().registerConsumers(new TrackNumberLookup());

        vertx.deployVerticle(new LogisticsVerticle());
        vertx.deployVerticle(new CustomersVerticle());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ParcelReceipt parcelReceipt = packageReception.accept();
//
//        LocalDate estimatedDeliveryTime = parcelReceipt.getDeliveryTime().getEstimatedDeliveryTime();
//
//        long price = parcelReceipt.getPrice().getValue();
//
//        assertEquals(parcelReceipt.getParcel(), parcel);
//        assertEquals(LocalDate.now().plusDays(10), estimatedDeliveryTime);
//        assertEquals(545012L, price);
    }
}
