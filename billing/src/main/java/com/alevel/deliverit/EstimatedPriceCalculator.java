package com.alevel.deliverit;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import com.alevel.deliverit.logistics.PostalAddress;

public class EstimatedPriceCalculator {
    public Money calculate(Parcel parcel, Sender sender, PostalAddress destination) {
        System.out.println("Money is null");
        return null;
    }
}
