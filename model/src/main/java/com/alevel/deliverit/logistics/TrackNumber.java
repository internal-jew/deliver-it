package com.alevel.deliverit.logistics;

import com.alevel.deliverit.customers.ParcelId;
import com.alevel.deliverit.entity.EntityId;

import java.util.UUID;

/**
 * @author Vadym Mitin
 */
public class TrackNumber extends EntityId<TrackNumberId> {
    private final ParcelId parcelId; // a reference to the unique id of a unique parcel that associated with this track number
    private final UUID uuid;

    public TrackNumber(TrackNumberId id, ParcelId parcelId, UUID uuid) {
        super(id);
        this.parcelId = parcelId;
        this.uuid = uuid;
    }

    public ParcelId getParcelId() {
        return parcelId;
    }

    public UUID getUuid() {
        return uuid;
    }

}
