package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Parser;
import com.alevel.deliverit.customers.Sender;

/**
 * @author Vitalii Usatyi
 */
class ParcelReceptionRequest {
    private Sender sender;
    private Parcel parcel;

    Sender getSender() {
        return sender;
    }

    Parcel getParcel() {
        return parcel;
    }

    ParcelReceptionRequest(Sender sender, Parcel parcel) {
        this.sender = sender;
        this.parcel = parcel;
    }

    static Parser<ParcelReceptionRequest> parser() {
        return new ParcelReceptionRequestParser();
    }
}
