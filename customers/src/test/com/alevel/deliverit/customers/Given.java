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
        return new ParcelId(10L);
    }

    public static SenderId givenSenderId() {
        return new SenderId(11L);
    }

    public static Weight givenWeight() {
        return new Weight(25, WeightUnit.KILOGRAM);
    }

    public static SenderProfile givenSenderProfile() {
        return new SenderProfile(givenName(), givenPostalAddress("pu_9" ), givenCountry());
    }

    public static PostalAddress givenPostalAddress(String postCode) {
        return PostalAddress.builder()
                .setFirstName("Vasily")
                .setLastName("Petrovich")
                .setHouseNumber("1024")
                .setCountry(givenCountry())
                .setCity("Mukhosransk")
                .setApartmentNumber("999")
                .setPostalCode(postCode)
                .setStreet("Sumskaya")
                .build();
    }

    public static Parcel givenParcel() {
        return new Parcel(givenParcelId(), givenWeight(), givenPostalAddress("PU_1"));
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
