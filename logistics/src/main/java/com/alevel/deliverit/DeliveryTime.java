package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.PostalAddress;

public class DeliveryTime {
    public EstimatedDeliveryTime estimate(Parcel parcel, Sender sender, PostalAddress destination) {
        System.out.println("EstimatedDeliveryTime is null");
        return null;
    }
}
