package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.logistics.*;

/**
 * @author Sergey Bogovesov
 */
public class Given {

    public static Country givenCountry() {
        return new Country("Ukraine", "UA");
    }

    public static Name givenName() {
        return new Name("John");
    }

    public static ParcelId givenParcelId() {
        return new ParcelId(1111L);
    }

    public static ParcelId givenParcelId2() {
        return new ParcelId(2222L);
    }

    public static SenderId givenSenderId() {
        return new SenderId(11L);
    }

    public static Weight givenWeight() {
        return new Weight(25, WeightUnit.KILOGRAM);
    }

    public static SenderProfile givenSenderProfile() {
        return new SenderProfile(givenName(), givenPostalAddress(), givenCountry());
    }

    public static long givenStartPostOffice() {
        return 1L;
    }

    public static long givenStartPostOffice2() {
        return 8L;
    }

    public static long givenFinishPostOffice() {
        return 9L;
    }

    public static long givenFinishPostOffice2() {
        return 7L;
    }

    public static PostalAddress givenPostalAddress() {
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

    public static Parcel givenParcel() {
        return new Parcel(givenParcelId(), givenWeight(), givenPostalAddress(), givenStartPostOffice(), givenFinishPostOffice());
    }

    public static Parcel givenParcel2() {
        return new Parcel(givenParcelId2(), givenWeight(), givenPostalAddress(), givenStartPostOffice2(), givenFinishPostOffice2());
    }

    public static Sender givenSender() {
        return new Sender(givenSenderId(), givenSenderProfile());
    }

    public static DeliveryTime getDeliveryTime() {
        return new DeliveryTime();
    }

    public static EstimatedPriceCalculator getEstimatedPriceCalculator() {
        return new EstimatedPriceCalculator();
    }

    public static TrackNumberRepository getTrackNumbers() {
        return TrackNumberRepository.getInstance();
    }
}
