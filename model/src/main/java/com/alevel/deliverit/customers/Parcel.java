package com.alevel.deliverit.customers;

import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.entity.Entity;

/**
 * @author Vadym Mitin
 */
public class Parcel extends Entity<ParcelId> {
    private final Weight weight;
    private final PostalAddress deliveryAddress;

    protected Parcel(ParcelId id, Weight weight, PostalAddress deliveryAddress) {
        super(id);
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
    }
}
