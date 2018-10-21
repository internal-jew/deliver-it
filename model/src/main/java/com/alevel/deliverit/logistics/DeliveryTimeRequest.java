package com.alevel.deliverit.logistics;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.postal.network.Route;

public class DeliveryTimeRequest {
    private final Parcel parcel;
    private final Route route;

    public DeliveryTimeRequest(Parcel parcel, Route route) {
        this.parcel = parcel;
        this.route = route;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public Route getRoute() {
        return route;
    }
}
