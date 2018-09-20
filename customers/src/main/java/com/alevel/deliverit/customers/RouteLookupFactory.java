package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;

public class RouteLookupFactory {
    public static RouteLookupRequest create(Parcel parcel, Sender sender) {
        // TODO Implements this
        return new RouteLookupRequest(1l, 2l);
    }
}
