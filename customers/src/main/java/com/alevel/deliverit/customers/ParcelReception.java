package com.alevel.deliverit.customers;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.factory.RequestLookupFactory;
import com.alevel.deliverit.customers.gateway.BillingGateway;
import com.alevel.deliverit.customers.gateway.LogisticsGateway;
import com.alevel.deliverit.customers.request.PriceLookupRequest;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.gateway.EstimatedPriceCalculator;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberRepository;
import com.alevel.deliverit.logistics.TrackNumberRequest;
import com.alevel.deliverit.logistics.postal.network.Route;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;
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

    private EstimatedPriceCalculator estimatedPriceCalculator;
    private DeliveryTime deliveryTime;
    private TrackNumberRepository trackNumbers;

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
                getTrackNumber(trackNumber -> {
                    result.accept(ParcelReceipt
                            .builder()
                            .setParcel(parcel)
                            .setDeliveryTime(new EstimatedDeliveryTime(LocalDate.now()))
                            .setPrice(new Money(123, Currency.getInstance(Locale.CANADA)))
                            .setTrackNumber(trackNumber)
                            .build());
                });
            });
        });
    }

    private TrackNumber getTrackNumber(Consumer<TrackNumber> callback) {
        //        TrackNumber trackNumber = trackNumbers.registerParcel(parcel);
        TrackNumberRequest trackNumberRequest = RequestLookupFactory.newTrackNumberRequest(parcel);
        return LogisticsGateway.registerParcel(trackNumberRequest, callback);
    }

//    private EstimatedDeliveryTime getDeliveryTime(Route route) {
//        //        EstimatedDeliveryTime estimatedDeliveryTime = deliveryTime.estimate(parcel, route);
//        DeliveryTimeRequest deliveryTimeRequest = RequestLookupFactory.newDeliveryTimeRequest(parcel, route);
//        return LogisticsGateway.estimate(deliveryTimeRequest);
//    }

    private Money getMoney(Route route, Consumer<Money> callback) {
        //        Money price = estimatedPriceCalculator.calculate(parcel.getWeight(), route);
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
