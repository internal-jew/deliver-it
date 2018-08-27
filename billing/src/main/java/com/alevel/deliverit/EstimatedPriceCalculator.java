package com.alevel.deliverit;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import com.alevel.deliverit.logistics.PostalAddress;

/**
 * @author Sergey Bogovesov
 */
public class EstimatedPriceCalculator {

    public Money calculate(Parcel parcel, Sender sender, PostalAddress destination) {
        // TODO https://github.com/internal-jew/deliver-it/issues/11
        System.out.println("`EstimatePriceCalculator.calculate` is not yet implemented");
        return null;
    }
}
