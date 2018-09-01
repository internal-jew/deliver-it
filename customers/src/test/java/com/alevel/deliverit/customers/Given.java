package com.alevel.deliverit.customers;

import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.WeightUnit;

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
        return new ParcelId(10l);
    }

    public static SenderId givenSenderId() {
        return new SenderId(11l);
    }

    public static Weight givenWeight() {
        return new Weight(25, WeightUnit.KILOGRAM);
    }

    public static SenderProfile givenSenderProfile() {
        return new SenderProfile(givenName(), givenPostalAddress(), givenCountry());
    }

    public static PostalAddress givenPostalAddress() {
        return new PostalAddress(givenCountry(), "some address");
    }

    public static Parcel givenParcel() {
        return new Parcel(givenParcelId(), givenWeight(), givenPostalAddress());
    }

    public static Sender givenSender() {
        return new Sender(givenSenderId(), givenSenderProfile());
    }
}
