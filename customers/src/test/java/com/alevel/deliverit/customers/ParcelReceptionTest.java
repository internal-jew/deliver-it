package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.TrackNumbers;
import com.alevel.deliverit.logistics.PostalAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        PostalAddress destination = Given.givenPostalAddress();
        Parcel parcel = Given.givenParcel();
        Sender sender = Given.givenSender();

        final ParcelReception packageReception = ParcelReception
                .builder()
                .setParcel(parcel)
                .setDestination(destination)
                .setSender(sender)
                .build();

        packageReception.setDeliveryTime(getDeliveryTime());
        packageReception.setEstimatedPriceCalculator(getEstimatedPriceCalculator());
        packageReception.setTrackNumbers(getTrackNumbers());

        ParcelReceipt parcelReceipt = packageReception.accept();

        assertEquals(parcelReceipt.getParcel(), parcel);
        //TODO https://github.com/internal-jew/deliver-it/issues/14
    }

    public DeliveryTime getDeliveryTime(){
        return mock(DeliveryTime.class);
    }

    public EstimatedPriceCalculator getEstimatedPriceCalculator(){
        return mock(EstimatedPriceCalculator.class);
    }

    public TrackNumbers getTrackNumbers(){
        return mock(TrackNumbers.class);
    }
}
