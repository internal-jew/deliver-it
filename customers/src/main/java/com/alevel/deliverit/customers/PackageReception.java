package com.alevel.deliverit.customers;

import com.alevel.deliverit.*;
import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.TrackNumber;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

/**
 * Implements of package reception from a sender.
 *
 * @author Sergey Bogovesov
 */
public class PackageReception {
    private Parcel parcel;
    private Sender sender;
    private PostalAddress destination;

    private EstimatedPriceCalculator estimatedPriceCalculator;
    private DeliveryTime deliveryTime;
    private TrackNumbers trackNumbers;

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Implements the scenarios of package reception from a sender.
     *
     * @return {@link PackageReceipt package receipt}
     */
    public PackageReceipt accept() {
        Money price = estimatedPriceCalculator.calculate(parcel, sender, destination);
        EstimatedDeliveryTime estimatedDeliveryTime = deliveryTime.estimate(parcel, sender, destination);
        TrackNumber trackNumber = trackNumbers.issue(parcel);

        return PackageReceipt
                .builder()
                .setParcel(parcel)
                .setDeliveryTime(estimatedDeliveryTime)
                .setPrice(price)
                .setTrackNumber(trackNumber)
                .build();
    }

    private PackageReception(Parcel parcel, Sender sender, PostalAddress destination) {
        this.parcel = parcel;
        this.sender = sender;
        this.destination = destination;
    }

    @VisibleForTesting
    public void setEstimatedPriceCalculator(EstimatedPriceCalculator estimatedPriceCalculator) {
        this.estimatedPriceCalculator = estimatedPriceCalculator;
    }

    @VisibleForTesting
    public void setDeliveryTime(DeliveryTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @VisibleForTesting
    public void setTrackNumbers(TrackNumbers trackNumbers) {
        this.trackNumbers = trackNumbers;
    }

    public static class Builder {
        private Parcel parcel;
        private Sender sender;
        private PostalAddress destination;

        private Builder() {
        }

        public Builder setParcel(Parcel parcel) {
            this.parcel = parcel;
            return this;
        }

        public Builder setSender(Sender sender) {
            this.sender = sender;
            return this;
        }

        public Builder setDestination(PostalAddress destination) {
            this.destination = destination;
            return this;
        }

        public PackageReception build() {
            Preconditions.checkNotNull(parcel);
            Preconditions.checkNotNull(sender);
            Preconditions.checkNotNull(destination);

            return new PackageReception(parcel, sender, destination);
        }
    }

}
