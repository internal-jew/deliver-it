package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.TrackNumbers;
import com.alevel.deliverit.logistics.PostalAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

@DisplayName("PackageReception should")
class PackageReceptionTest {

    @Test
    @DisplayName("Receiving a package from the sender")
    void accept() {
        PostalAddress destination = GivenJava.givenPostalAddress();
        Parcel parcel = GivenJava.givenParcel();
        Sender sender = GivenJava.givenSender();

        final PackageReception packageReception = PackageReception
                .builder()
                .setParcel(parcel)
                .setDestination(destination)
                .setSender(sender)
                .build();

        packageReception.setDeliveryTime(getDeliveryTime());
        packageReception.setEstimatedPriceCalculator(getEstimatedPriceCalculator());
        packageReception.setTrackNumbers(getTrackNumbers());

        PackageReceipt packageReceipt = packageReception.accept();

        Assertions.assertEquals(packageReceipt.getParcel(), parcel);
        //TODO write a few tests for check the price, track number, delivery time
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
