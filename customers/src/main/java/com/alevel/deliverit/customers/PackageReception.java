package com.alevel.deliverit.customers;

import com.alevel.deliverit.*;
import com.alevel.deliverit.Package;

/**
 * Implements of package reception from a sender
 *
 * @author Bogovesov Sergey
 */
public class PackageReception {
    private Package aPackage;
    private Sender sender;
    private PostalAddress postalAddress;

    /**
     * Implements the scenarios of package reception from a sender
     *
     * @return Value object PackageReceipt which contains all information about the package
     */
    public PackageReceipt accept() {
        Money price = Billing.calcEstimatePrice(aPackage, postalAddress);
        EstimatedDeliveryTime deliveryTime = Logistics.calcEstimatedDeliveryTime(postalAddress);
        TrackNumber trackNumber = Logistics.buildTrackNumber(aPackage);

        return PackageReceipt
                .builder()
                .setPackage(aPackage)
                .setDeliveryTime(deliveryTime)
                .setPrice(price)
                .setTrackNumber(trackNumber)
                .build();
    }

    private PackageReception() {
    }

    private PackageReception(Package aPackage, Sender sender, PostalAddress postalAddress) {
        this.aPackage = aPackage;
        this.sender = sender;
        this.postalAddress = postalAddress;
    }

    public static Builder builder() {
        return new PackageReception().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setPackage(Package aPackage) {
            PackageReception.this.aPackage = aPackage;
            return this;
        }

        public Builder setSender(Sender sender) {
            PackageReception.this.sender = sender;
            return this;
        }

        public Builder setPostalAddress(PostalAddress postalAddress) {
            PackageReception.this.postalAddress = postalAddress;
            return this;
        }

        public PackageReception build() {
            if (aPackage == null) {
                throw new IllegalArgumentException("Package cant be null!");
            }
            if (sender == null) {
                throw new IllegalArgumentException("Sender cant be null!");
            }
            if (postalAddress == null) {
                throw new IllegalArgumentException("Postal address cant be null!");
            }
            return new PackageReception(aPackage, sender, postalAddress);
        }
    }
}
