package com.alevel.deliverit.customers.request;

import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.postal.network.Route;

/**
 * @author Sergey Bogovesov
 */
public class PriceLookupRequest {
    private final Weight parcelWeight;
    private final Route route;

    public PriceLookupRequest(Weight parcelWeight, Route route) {
        this.parcelWeight = parcelWeight;
        this.route = route;
    }

    public Weight getParcelWeight() {
        return parcelWeight;
    }

    public Route getRoute() {
        return route;
    }
}
