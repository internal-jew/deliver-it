package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.PostalAddress;

/**
 * @author Sergey Bogovesov
 */
public class DeliveryTime {

    public EstimatedDeliveryTime estimate(Parcel parcel, Sender sender) {
        //TODO https://github.com/internal-jew/deliver-it/issues/12
        System.out.println("`DeliveryTime.estimate` is not yet implemented");
        return null;
    }
}
