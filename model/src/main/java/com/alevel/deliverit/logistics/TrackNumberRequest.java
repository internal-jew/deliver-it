package com.alevel.deliverit.logistics;

import com.alevel.deliverit.customers.Parcel;

import java.io.Serializable;

/**
 * @author Sergey Bogovesov
 */
public class TrackNumberRequest implements Serializable {
    private final Parcel parcel;

    public TrackNumberRequest(Parcel parcel) {
        this.parcel = parcel;
    }

    public Parcel getParcel() {
        return parcel;
    }
}
