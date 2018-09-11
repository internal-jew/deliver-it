package com.alevel.deliverit.customers;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupRequest {
    private Parcel parcel;
    private Sender sender;

    public RouteLookupRequest(Parcel parcel, Sender sender) {
        this.parcel = parcel;
        this.sender = sender;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
