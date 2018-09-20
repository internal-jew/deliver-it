package com.alevel.deliverit.customers.request;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import com.alevel.deliverit.logistics.postal.network.PostalUnit;
import com.alevel.deliverit.postal.network.PostalNetwork;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupRequest {
    private Parcel parcel;
    private Sender sender;
    private PostalUnit startUnit;
    private PostalUnit endUnit;

    public RouteLookupRequest(Parcel parcel, Sender sender) {
        this.parcel = parcel;
        this.sender = sender;
    }

    public PostalUnit getStartUnit() {
        final String postalCode = parcel.getDeliveryAddress().getPostalCode();
        startUnit = PostalNetwork.instance().find(postalCode);
        return startUnit;
    }

    public PostalUnit getEndUnit() {
        final String postalCode = sender.getSenderProfile().getAddress().getPostalCode();
        endUnit = PostalNetwork.instance().find(postalCode);
        return endUnit;
    }
}
