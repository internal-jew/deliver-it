package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelReception;
import com.alevel.deliverit.customers.Sender;

/**
 * @author Vitalii Usatyi
 */
public class Endpoint {
    private JsonParser jsonParser = new JsonParser();

    void run(String jsonString) {

        Parcel parcel = (Parcel) Parcel.parser().parse(jsonString);
        Sender sender = (Sender) Sender.parser().parse(jsonString);
        ParcelReception parcelReception = buildParcelReception(parcel, sender);
    }

    private ParcelReception buildParcelReception(Parcel parcel, Sender sender) {
        return ParcelReception
                .builder()
                .setParcel(parcel)
                .setSender(sender)
                .build();
    }
}
