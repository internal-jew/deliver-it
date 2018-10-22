package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.logistics.TrackNumberRepository;
import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.customers.tracking.handler.ParcelTrackingHandler;

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
    private TrackNumberRepository trackNumbers;

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

        Money price = estimatedPriceCalculator.calculate(parcel.getWeight(), route);
        EstimatedDeliveryTime estimatedDeliveryTime = deliveryTime.estimate(parcel, route);
        TrackNumber trackNumber = trackNumbers.registerParcel(parcel);

        ParcelReceipt parcelReceipt = ParcelReceipt
                .builder()
                .setParcel(parcel)
                .setDeliveryTime(estimatedDeliveryTime)
                .setPrice(price)
                .setTrackNumber(trackNumber)
                .setRoute(route)
                .build();


        ParcelTrackingHandler.instance().registerReceipt(parcelReceipt);

        return parcelReceipt;
    }

    private ParcelReception(Parcel parcel,
                            Sender sender,
                            EstimatedPriceCalculator estimatedPriceCalculator,
                            DeliveryTime deliveryTime,
                            TrackNumberRepository trackNumbers) {
        this.parcel = parcel;
        this.sender = sender;
        this.estimatedPriceCalculator = estimatedPriceCalculator;
        this.deliveryTime = deliveryTime;
        this.trackNumbers = trackNumbers;
    }

    public static class Builder {
        private Parcel parcel;
        private Sender sender;
        private EstimatedPriceCalculator estimatedPriceCalculator;
        private DeliveryTime deliveryTime;
        private TrackNumberRepository trackNumbers;

        private Builder() {
        }

        public Builder setEstimatedPriceCalculator(EstimatedPriceCalculator estimatedPriceCalculator) {
            this.estimatedPriceCalculator = estimatedPriceCalculator;
            return this;
        }

        public Builder setDeliveryTime(DeliveryTime deliveryTime) {
            this.deliveryTime = deliveryTime;
            return this;
        }

        public Builder setTrackNumbers(TrackNumberRepository trackNumbers) {
            this.trackNumbers = trackNumbers;
            return this;
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
            checkNotNull(estimatedPriceCalculator);
            checkNotNull(deliveryTime);
            checkNotNull(trackNumbers);

            return new ParcelReception(parcel, sender, estimatedPriceCalculator, deliveryTime, trackNumbers);
        }
    }
}
