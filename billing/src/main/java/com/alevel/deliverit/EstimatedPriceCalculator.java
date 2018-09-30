package com.alevel.deliverit;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.WeightUnit;
import com.alevel.deliverit.logistics.postal.network.Route;

/**
 * @author Sergey Bogovesov
 */
public class EstimatedPriceCalculator {

    public Money calculate(Weight parcelWeight, Route route) {
        // TODO https://github.com/internal-jew/deliver-it/issues/11
//        System.out.println("`EstimatePriceCalculator.calculate` is not yet implemented");
        Double value = parcelWeight.getValue();
        WeightUnit unit = parcelWeight.getUnit();
        int routeWeight = route.getWeight();

        Double

        return null;
    }
}
