package com.alevel.deliverit.customers;

import com.alevel.deliverit.*;
import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.TrackNumber;
import com.google.common.annotations.VisibleForTesting;

import static com.google.common.base.Preconditions.*;

/**
 * Implements of package reception from a sender.
 *
 * @author Sergey Bogovesov
 */
public class ParcelReception {
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
     * @return {@link ParcelReceipt package receipt}
     */
    public ParcelReceipt accept() {
        Money price = estimatedPriceCalculator.calculate(parcel, sender, destination);
        EstimatedDeliveryTime estimatedDeliveryTime = deliveryTime.estimate(parcel, sender, destination);
        TrackNumber trackNumber = trackNumbers.issue(parcel);

        return ParcelReceipt
                .builder()
                .setParcel(parcel)
                .setDeliveryTime(estimatedDeliveryTime)
                .setPrice(price)
                .setTrackNumber(trackNumber)
                .build();
    }

    private ParcelReception(Parcel parcel, Sender sender, PostalAddress destination) {
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

        public ParcelReception build() {
            checkNotNull(parcel);
            checkNotNull(sender);
            checkNotNull(destination);

            return new ParcelReception(parcel, sender, destination);
        }
    }

}
