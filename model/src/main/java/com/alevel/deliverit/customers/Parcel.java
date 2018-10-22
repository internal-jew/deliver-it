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

    public PostalAddress getDeliveryAddress() {
        return deliveryAddress;
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

//      __With this implementation it will be possible to create only unique parcels__
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof Parcel) {
//            Parcel parcel = (Parcel) obj;
//            if (obj == this) {
//                return true;
//            }
//            return parcel.getWeight().getUnit().equals(this.weight.getUnit())
//                    && parcel.getWeight().getValue().equals(this.getWeight().getValue())
//                    && parcel.getDeliveryAddress().getPostalCode().equals(this.deliveryAddress.getPostalCode())
//                    && parcel.getStartPostOfficeId() == this.startPostOfficeId
//                    && parcel.getFinishPostOfficeId() == this.finishPostOfficeId;
//        }
//        return false;
//    }
}
