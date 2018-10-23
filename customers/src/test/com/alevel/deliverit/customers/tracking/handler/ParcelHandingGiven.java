package com.alevel.deliverit.customers.tracking.handler;

import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.customers.*;
import com.alevel.deliverit.logistics.TrackNumberRepository;

/**
 * @author Vadym Mitin
 */
public class ParcelHandingGiven {
    public static ParcelReceipt createReceipt() {
        Parcel parcel = Given.givenParcel();
        Sender sender = Given.givenSender();
        return ParcelReception.builder()
                .setParcel(parcel)
                .setSender(sender)
                .setTrackNumbers(TrackNumberRepository.getInstance())
                .setDeliveryTime(new DeliveryTime())
                .setEstimatedPriceCalculator(new EstimatedPriceCalculator())
                .build()
                .accept();
    }

    public static ParcelReceipt createReverseReceipt() {
        Parcel parcel = Given.givenParcel2();
        Sender sender = Given.givenSender();
        return ParcelReception.builder()
                .setParcel(parcel)
                .setSender(sender)
                .setTrackNumbers(TrackNumberRepository.getInstance())
                .setDeliveryTime(new DeliveryTime())
                .setEstimatedPriceCalculator(new EstimatedPriceCalculator())
                .build()
                .accept();
    }
}
