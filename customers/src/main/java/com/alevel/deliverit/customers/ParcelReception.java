package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.TrackNumbers;
import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.google.common.annotations.VisibleForTesting;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implements of package reception from a sender.
 *
 * @author Sergey Bogovesov
 */
public class ParcelReception {
    private Parcel parcel;
    private Sender sender;

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
        RouteLookupRequest request = RouteLookupFactory.newRequest(parcel, sender);
        Route route = LogisticsGateway.find(request);

        Money price = estimatedPriceCalculator.calculate(parcel, sender);
        EstimatedDeliveryTime estimatedDeliveryTime = deliveryTime.estimate(parcel, sender);
        TrackNumber trackNumber = trackNumbers.issue(parcel);

        return ParcelReceipt
                .builder()
                .setParcel(parcel)
                .setDeliveryTime(estimatedDeliveryTime)
                .setPrice(price)
                .setTrackNumber(trackNumber)
                .build();
    }

    private ParcelReception(Parcel parcel, Sender sender) {
        this.parcel = parcel;
        this.sender = sender;
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

        public ParcelReception build() {
            checkNotNull(parcel);
            checkNotNull(sender);

            return new ParcelReception(parcel, sender);
        }
    }

}
