package com.alevel.deliverit;

import com.alevel.deliverit.customers.ParcelReception;

/**
 * @author Vitalii Usatyi
 */
public class Endpoint {

    private JsonParser jsonParser = new JsonParser();

    void run(String jsonString) {
        ParcelReceptionRequest request = ParcelReceptionRequest.parser().parse(jsonString);
        ParcelReception parcelReception =
                ParcelReception
                        .builder()
                        .setSender(request.getSender())
                        .setParcel(request.getParcel())
                        .build();
        parcelReception.accept();
    }


}
