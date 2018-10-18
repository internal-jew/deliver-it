package com.alevel.deliverit.fsm;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.customers.*;
import com.alevel.deliverit.logistics.*;

/**
 * @author Vadym Mitin
 */
public class FSMGiven {
    private static Country givenCountry() {
        return new Country("Ukraine", "UA");
    }

    private static Name givenName() {
        return new Name("John");
    }

    private static ParcelId givenParcelId() {
        return new ParcelId(10L);
    }

    private static SenderId givenSenderId() {
        return new SenderId(11L);
    }

    private static Weight givenWeight() {
        return new Weight(25, WeightUnit.KILOGRAM);
    }

    private static SenderProfile givenSenderProfile() {
        return new SenderProfile(givenName(), givenPostalAddress(), givenCountry());
    }

    private static long givenStartPostOffice() {
        return 1L;
    }

    private static long givenFinishPostOffice() {
        return 9L;
    }

    private static long givenStartPostOffice2() {
        return 1L;
    }

    private static long givenFinishPostOffice2() {
        return 4L;
    }

    private static PostalAddress givenPostalAddress() {
        return PostalAddress.builder()
                .setFirstName("Vasily")
                .setLastName("Petrovich")
                .setHouseNumber("1024")
                .setCountry(givenCountry())
                .setCity("Mukhosransk")
                .setApartmentNumber("999")
                .setPostalCode("666")
                .setStreet("Sumskaya")
                .build();
    }

    static Parcel givenParcel() {
        return new Parcel(givenParcelId(), givenWeight(), givenPostalAddress(), givenStartPostOffice(), givenFinishPostOffice());
    }

    static Parcel givenParcel2() {
        return new Parcel(givenParcelId(), givenWeight(), givenPostalAddress(), givenStartPostOffice2(), givenFinishPostOffice2());
    }

    private static Sender givenSender() {
        return new Sender(givenSenderId(), givenSenderProfile());
    }

    private static DeliveryTime getDeliveryTime() {
        return new DeliveryTime();
    }

    private static EstimatedPriceCalculator getEstimatedPriceCalculator() {
        return new EstimatedPriceCalculator();
    }

    private static TrackNumberRepository getTrackNumbers() {
        return TrackNumberRepository.getInstance();
    }
}
