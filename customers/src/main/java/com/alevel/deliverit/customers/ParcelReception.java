package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.factory.RequestLookupFactory;
import com.alevel.deliverit.customers.gateway.BillingGateway;
import com.alevel.deliverit.customers.gateway.LogisticsGateway;
import com.alevel.deliverit.customers.request.PriceLookupRequest;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.*;
import com.alevel.deliverit.logistics.postal.network.Route;

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
        Route route = getRoute();

        Money price = getMoney(route);

        EstimatedDeliveryTime estimatedDeliveryTime = getDeliveryTime(route);

        TrackNumber trackNumber = getTrackNumber();

        return ParcelReceipt
                .builder()
                .setParcel(parcel)
                .setDeliveryTime(estimatedDeliveryTime)
                .setPrice(price)
                .setTrackNumber(trackNumber)
                .build();
    }

    private TrackNumber getTrackNumber() {
        //        TrackNumber trackNumber = trackNumbers.registerParcel(parcel);
        TrackNumberRequest trackNumberRequest = RequestLookupFactory.newTrackNumberRequest(parcel);
        return LogisticsGateway.registerParcel(trackNumberRequest);
    }

    private EstimatedDeliveryTime getDeliveryTime(Route route) {
        //        EstimatedDeliveryTime estimatedDeliveryTime = deliveryTime.estimate(parcel, route);
        DeliveryTimeRequest deliveryTimeRequest = RequestLookupFactory.newDeliveryTimeRequest(parcel, route);
        return LogisticsGateway.estimate(deliveryTimeRequest);
    }

    private Money getMoney(Route route) {
        //        Money price = estimatedPriceCalculator.calculate(parcel.getWeight(), route);
        PriceLookupRequest priceLookupRequest = RequestLookupFactory.newPriceRequest(parcel, route);
        return BillingGateway.estimatedPrice(priceLookupRequest);
    }

    private Route getRoute() {
        RouteLookupRequest request = RequestLookupFactory.newRouteRequest(parcel, sender);
        return LogisticsGateway.find(request);
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
