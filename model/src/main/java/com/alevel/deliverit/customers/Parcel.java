package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.entity.Entity;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.postal.network.PostOffice;

/**
 * @author Vadym Mitin
 */
public class Parcel extends Entity<ParcelId> {
    private final Weight weight;
    private final PostalAddress deliveryAddress;
    private final long startPostOfficeId;
    private final long finishPostOfficeId;

    public Parcel(ParcelId id, Weight weight, PostalAddress deliveryAddress, long startPostOfficeId, long finishPostOfficeId) {
        super(id);
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.startPostOfficeId = startPostOfficeId;
        this.finishPostOfficeId = finishPostOfficeId;
    }

    public Weight getWeight() {
        return weight;
    }

    public static Parser<Parcel> parser() {
        return new ParcelParser();
    }

    public long getStartPostOfficeId() {
        return startPostOfficeId;
    }

    public long getFinishPostOfficeId() {
        return finishPostOfficeId;
    }
}
