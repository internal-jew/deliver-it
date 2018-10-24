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

import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implements of package reception from a sender.
 *
 * @author Sergey Bogovesov
 */
public class ParcelReception {
    private Parcel parcel;
    private Sender sender;

    private ParcelReception(Parcel parcel, Sender sender) {
        this.parcel = parcel;
        this.sender = sender;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Implements the scenarios of package reception from a sender.
     *
     * @return {@link ParcelReceipt package receipt}
     */
    public void accept(Consumer<ParcelReceipt> result) {
        getRoute(route -> {
            getMoney(route, money -> {
                getDeliveryTime(route, deliveryTime -> {
                    getTrackNumber(trackNumber -> {
                        result.accept(ParcelReceipt
                                .builder()
                                .setParcel(parcel)
                                .setDeliveryTime(deliveryTime)
                                .setPrice(money)
                                .setTrackNumber(trackNumber)
                                .build());
                    });
                });
            });
        });
    }

    private TrackNumber getTrackNumber(Consumer<TrackNumber> callback) {
        TrackNumberRequest trackNumberRequest = RequestLookupFactory.newTrackNumberRequest(parcel);
        return LogisticsGateway.registerParcel(trackNumberRequest, callback);
    }

    private EstimatedDeliveryTime getDeliveryTime(Route route, Consumer<EstimatedDeliveryTime> callback) {
        DeliveryTimeRequest deliveryTimeRequest = RequestLookupFactory.newDeliveryTimeRequest(parcel, route);
        return LogisticsGateway.estimate(deliveryTimeRequest, callback);
    }

    private Money getMoney(Route route, Consumer<Money> callback) {
        PriceLookupRequest priceLookupRequest = RequestLookupFactory.newPriceRequest(parcel, route);
        return BillingGateway.estimatedPrice(priceLookupRequest, callback);
    }

    private Route getRoute(Consumer<Route> callback) {
        RouteLookupRequest request = RequestLookupFactory.newRouteRequest(parcel, sender);
        return LogisticsGateway.find(request, callback);
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
