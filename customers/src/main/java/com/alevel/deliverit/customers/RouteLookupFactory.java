package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupFactory {
    public static RouteLookupRequest newRequest(Parcel parcel, Sender sender) {
        // TODO Implement this
        return new RouteLookupRequest(1l, 2l);
    }
}
