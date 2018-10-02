package com.alevel.deliverit.customers;

import com.alevel.deliverit.entity.Entity;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberRepository;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.postal.network.PostOffice;

/**
 * @author Vadym Mitin
 */
public class Parcel extends Entity<ParcelId> {
    private final Weight weight;
    private final PostalAddress deliveryAddress;
    private final PostOffice startPostOffice;
    private final PostOffice finishPostOffice;

    public Parcel(ParcelId id, Weight weight, PostalAddress deliveryAddress, PostOffice startPostOffice, PostOffice finishPostOffice) {
        super(id);
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.startPostOffice = startPostOffice;
        this.finishPostOffice = finishPostOffice;
    }

    public Weight getWeight() {
        return weight;
    }

    public PostalAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public PostOffice getStartPostOffice() {
        return startPostOffice;
    }

    public PostOffice getFinishPostOffice() {
        return finishPostOffice;
    }
}
