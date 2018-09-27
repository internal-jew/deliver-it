package com.alevel.deliverit.customers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.alevel.deliverit.customers.Given.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

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
        packageReception.setTrackNumberRepository(getTrackNumbers());

        ParcelReceipt parcelReceipt = packageReception.accept();

        assertEquals(parcelReceipt.getParcel(), parcel);
        //TODO https://github.com/internal-jew/deliver-it/issues/14
    }

}
