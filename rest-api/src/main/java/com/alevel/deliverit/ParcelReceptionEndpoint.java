package com.alevel.deliverit;

import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.customers.ParcelReception;

import java.util.function.Consumer;

/**
 * @author Vitalii Usatyi
 */
public class ParcelReceptionEndpoint {

    void accept(String jsonString, Consumer<ParcelReceipt> callback) {
        ParcelReceptionRequest request = ParcelReceptionRequest.parser().parse(jsonString);
        ParcelReception parcelReception =
                ParcelReception
                        .builder()
                        .setSender(request.getSender())
                        .setParcel(request.getParcel())
                        .build();
        parcelReception.accept(callback);
    }
}
