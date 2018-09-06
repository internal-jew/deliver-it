package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
public class ObjectParser {
    private Parcel parcel;
    private Sender sender;

    /**
     * Parse JSON request to java objects: {@link Parcel} and {@link Sender}
     *
     * @param jsonObject is a JSON request
     */
    void parseToObject(JSONObject jsonObject) {
        sender = new SenderFactory().create(jsonObject);
        parcel = new ParcelFactory().create(jsonObject);
    }

    Parcel getParcel() {
        return parcel;
    }

    Sender getSender() {
        return sender;
    }
}
