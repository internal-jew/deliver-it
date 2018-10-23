package com.alevel.deliverit.customers;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.factory.RequestLookupFactory;
import com.alevel.deliverit.customers.gateway.BillingGateway;
import com.alevel.deliverit.customers.gateway.LogisticsGateway;
import com.alevel.deliverit.customers.request.PriceLookupRequest;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.DeliveryTimeRequest;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberRequest;
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

        checkNotNull(route);

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
        TrackNumberRequest trackNumberRequest = RequestLookupFactory.newTrackNumberRequest(parcel);
        return LogisticsGateway.registerParcel(trackNumberRequest);
    }

    private EstimatedDeliveryTime getDeliveryTime(Route route) {
        DeliveryTimeRequest deliveryTimeRequest = RequestLookupFactory.newDeliveryTimeRequest(parcel, route);
        return LogisticsGateway.estimate(deliveryTimeRequest);
    }

    private Money getMoney(Route route) {
        PriceLookupRequest priceLookupRequest = RequestLookupFactory.newPriceRequest(parcel, route);
        return BillingGateway.estimatedPrice(priceLookupRequest);
    }

    private Route getRoute() {
        RouteLookupRequest request = RequestLookupFactory.newRouteRequest(parcel, sender);
        return LogisticsGateway.find(request);
    }

    private ParcelReception(Parcel parcel, Sender sender) {
        this.parcel = parcel;
        this.sender = sender;
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
