package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelId;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberId;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Sergey Bogovesov
 */
public class TrackNumberRepository {
    private static final Map<TrackNumber, Parcel> TRACK_NUMBERS = new HashMap<>();

    private TrackNumberRepository() {

    }

    public static TrackNumberRepository getInstance() {
        return Singletone.INSTANCE.instance;
    }

    public TrackNumber registerParcel(Parcel parcel) {
        if (!TRACK_NUMBERS.containsValue(parcel)) {
            UUID uuid = UUID.randomUUID();
            ParcelId parcelId = parcel.getId();
            Random rnd = new Random();
            Long randomLong = rnd.nextLong();
            String trackNumberId = randomLong.toString();
            TrackNumber tr = new TrackNumber(new TrackNumberId(trackNumberId), parcelId, uuid);
            TRACK_NUMBERS.put(tr, parcel);
            return tr;
        }
        System.out.println("`TrackNumberRepository.registerParcel` is not yet implemented");
        return null;
    }

    public static ImmutableMap<TrackNumber, Parcel> getTrackNumbers() {
        return ImmutableMap.copyOf(TRACK_NUMBERS);
    }

    private enum Singletone {
        INSTANCE;
        private final TrackNumberRepository instance = new TrackNumberRepository();
    }
}
