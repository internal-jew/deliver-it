package com.alevel.deliverit.customers;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.postal.network.Route;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Sergey Bogovesov
 */
public class ParcelReceipt {
    private Money price;
    private Parcel parcel;
    private Route route;
    private EstimatedDeliveryTime deliveryTime;
    private TrackNumber trackNumber;

    public ParcelReceipt(Parcel parcel, Money price, EstimatedDeliveryTime deliveryTime, TrackNumber trackNumber, Route route) {
        this.price = price;
        this.parcel = parcel;
        this.deliveryTime = deliveryTime;
        this.trackNumber = trackNumber;
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    public Money getPrice() {
        return price;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public EstimatedDeliveryTime getDeliveryTime() {
        return deliveryTime;
    }

    public TrackNumber getTrackNumber() {
        return trackNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Money price;
        private Parcel parcel;
        private EstimatedDeliveryTime deliveryTime;
        private Route route;
        private TrackNumber trackNumber;

        public Builder setRoute(Route route) {
            this.route = route;
            return this;
        }

        public Builder setPrice(Money price) {
            this.price = price;
            return this;
        }

        public Builder setParcel(Parcel parcel) {
            this.parcel = parcel;
            return this;
        }

        public Builder setDeliveryTime(EstimatedDeliveryTime deliveryTime) {
            this.deliveryTime = deliveryTime;
            return this;
        }

        public Builder setTrackNumber(TrackNumber trackNumber) {
            this.trackNumber = trackNumber;
            return this;
        }

        public ParcelReceipt build() {
            checkNotNull(parcel);
            checkNotNull(price);
            checkNotNull(deliveryTime);
            checkNotNull(trackNumber);
            checkNotNull(route);

            return new ParcelReceipt(parcel, price, deliveryTime, trackNumber, route);
        }
    }
}
