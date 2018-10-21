package com.alevel.deliverit.logistics;

import com.alevel.deliverit.customers.Parcel;

/**
 * @author Sergey Bogovesov
 */
public class TrackNumberRequest {
    private final Parcel parcel;

    public TrackNumberRequest(Parcel parcel) {
        this.parcel = parcel;
    }

    public Parcel getParcel() {
        return parcel;
    }
}
