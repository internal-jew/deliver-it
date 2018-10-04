package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.logistics.TrackNumberRepository;
import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.WeightUnit;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.postal.network.PostNetwork;

import static org.mockito.Mockito.mock;

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
        return new SenderProfile(givenName(), givenPostalAddress(), givenCountry());
    }

    public static PostOffice givenStartPostOffice() {
        return PostNetwork.instance().find(1L).get();
    }

    public static PostOffice givenFinishPostOffice() {
        return PostNetwork.instance().find(9L).get();
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
