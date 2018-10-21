package com.alevel.deliverit.fsm;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.customers.*;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.gateway.RouteLookup;
import com.alevel.deliverit.logistics.*;
import com.alevel.deliverit.logistics.postal.network.Pair;
import com.alevel.deliverit.logistics.postal.network.Route;

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

    public static Parcel givenParcel() {
        return new Parcel(givenParcelId(), givenWeight(), givenPostalAddress(), givenStartPostOffice(), givenFinishPostOffice());
    }

    public static Parcel givenParcel2() {
        return new Parcel(givenParcelId(), givenWeight(), givenPostalAddress(), givenStartPostOffice2(), givenFinishPostOffice2());
    }

    public static Route givenRoute1() {
        RouteLookupRequest request = new RouteLookupRequest(givenStartPostOffice(), givenFinishPostOffice());
        return RouteLookup.find(request);
    }

    public static Route givenRoute2() {
        RouteLookupRequest request = new RouteLookupRequest(givenStartPostOffice2(), givenFinishPostOffice2());
        return RouteLookup.find(request);
    }

    public static Pair<Parcel, Route> givenPair1() {
        return new Pair<>(givenParcel(), givenRoute1());
    }

    public static Pair<Parcel, Route> givenPair2() {
        return new Pair<>(givenParcel2(), givenRoute2());
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
