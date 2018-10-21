package com.alevel.deliverit;

import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.customers.ParcelReception;
import com.alevel.deliverit.logistics.TrackNumberRepository;
import com.alevel.deliverit.tracking.handler.ParcelTrackingHandler;

/**
 * @author Vitalii Usatyi
 */
public class ParcelReceptionEndpoint {

    ParcelReceipt accept(String jsonString) {
        ParcelReceptionRequest request = ParcelReceptionRequest.parser().parse(jsonString);
        ParcelReception parcelReception = ParcelReception.builder()
                .setSender(request.getSender())
                .setParcel(request.getParcel())
                .setTrackNumbers(TrackNumberRepository.getInstance())
                .setEstimatedPriceCalculator(new EstimatedPriceCalculator())
                .setDeliveryTime(new DeliveryTime())
                .build();

        ParcelReceipt receipt = parcelReception.accept();
        ParcelTrackingHandler.instance().registerReceipt(receipt);
        return receipt;
    }
}
