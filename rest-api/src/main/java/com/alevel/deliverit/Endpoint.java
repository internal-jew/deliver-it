package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelReception;
import com.alevel.deliverit.customers.Sender;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
public class Endpoint {
    private ObjectParser objectParser = new ObjectParser();
    private JsonParser jsonParser = new JsonParser();

    void run(JSONObject jsonObject) {
        objectParser.parseToObject(jsonObject);
        ParcelReception parcelReception = buildParcelReception(objectParser.getParcel(), objectParser.getSender());
        parcelReception.accept();

    }

    private ParcelReception buildParcelReception(Parcel parcel, Sender sender) {
        return ParcelReception
                .builder()
                .setParcel(parcel)
                .setSender(sender)
                .build();
    }
}
