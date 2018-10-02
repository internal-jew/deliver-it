package com.alevel.deliverit.customers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
                .build();

        packageReception.setDeliveryTime(getDeliveryTime());
        packageReception.setEstimatedPriceCalculator(getEstimatedPriceCalculator());
        packageReception.setTrackNumbers(getTrackNumbers());

        ParcelReceipt parcelReceipt = packageReception.accept();

        LocalDate estimatedDeliveryTime = parcelReceipt.getDeliveryTime().getEstimatedDeliveryTime();

        long price = parcelReceipt.getPrice().getValue();

        assertEquals(parcelReceipt.getParcel(), parcel);
        assertEquals(LocalDate.now().plusDays(10), estimatedDeliveryTime);
        assertEquals(545012L, price);
    }
}
