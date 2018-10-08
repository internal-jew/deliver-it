package com.alevel.deliverit.logistics;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelId;
import com.google.common.collect.ImmutableMap;

import java.util.*;

/**
 * @author Sergey Bogovesov
 * @author Vadym Mitin
 */
public final class TrackNumberRepository {
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
            TrackNumber trackNumber = new TrackNumber(new TrackNumberId(trackNumberId), parcelId, uuid);
            TRACK_NUMBERS.put(trackNumber, parcel);
            return trackNumber;
        } else throw new IllegalArgumentException("this parcel already registered");
    }

    public ImmutableMap<TrackNumber, Parcel> getTrackNumbersMap() {
        return ImmutableMap.copyOf(TRACK_NUMBERS);
    }

    public void remove(TrackNumber trackNumber) {
        if (TRACK_NUMBERS.containsKey(trackNumber)) {
            TRACK_NUMBERS.remove(trackNumber);
        } else throw new NoSuchElementException("this track number does not exist");
    }

    public Parcel getParcelByTrackNumber(TrackNumber trackNumber) {
        if (TRACK_NUMBERS.containsKey(trackNumber)) {
            return TRACK_NUMBERS.get(trackNumber);
        } else throw new NoSuchElementException("this track number does not exist");
    }

    private enum Singletone {
        INSTANCE;
        private final TrackNumberRepository instance = new TrackNumberRepository();
    }
}
