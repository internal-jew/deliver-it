package com.alevel.deliverit.gateway;

import com.alevel.deliverit.BusinessLogicService;
import com.alevel.deliverit.Subscribe;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberRepository;
import com.alevel.deliverit.logistics.TrackNumberRequest;

import static com.alevel.deliverit.SubscribeAddress.LOGISTICS_TRACK_NUMBER_REGISTER_PARCEL;

/**
 * @author Sergey Bogovesov
 */
public class TrackNumberLookup implements BusinessLogicService {

    @Subscribe(LOGISTICS_TRACK_NUMBER_REGISTER_PARCEL)
    public static TrackNumber registerParcel(TrackNumberRequest request) {
        System.out.println("Register parcel " + request.getParcel().getName());
        return TrackNumberRepository.getInstance().registerParcel(request.getParcel());
    }
}
