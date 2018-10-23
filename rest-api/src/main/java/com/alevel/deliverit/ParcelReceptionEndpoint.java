package com.alevel.deliverit;

import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.customers.ParcelReception;
import com.alevel.deliverit.gateway.EstimatedPriceCalculator;
import com.alevel.deliverit.logistics.TrackNumberRepository;

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
                        .setTrackNumbers(TrackNumberRepository.getInstance())
                        .setEstimatedPriceCalculator(new EstimatedPriceCalculator())
                        .setDeliveryTime(new DeliveryTime())
                        .build();
        parcelReception.accept(callback);
    }
}
