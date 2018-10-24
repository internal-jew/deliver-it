package com.alevel.deliverit.gateway;

import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.postal.network.Route;

import java.util.Currency;

/**
 * @author Sergey Bogovesov
 * @author Vadym Mitin
 */
public class EstimatedPriceCalculator {

    public static final double WEIGHT_COEFFICIENT = 20.5;
    public static final double DISTANCE_COEFFICIENT = 0.2;

    public Money calculate(Weight parcelWeight, Route route) {
        Double weight = parcelWeight.getValue();

        int routeWeight = route.getWeight();

        int distanceKilometers = routeWeight * 1000;

        double distanceCost = estimateDistanceCost(distanceKilometers);

        double weightCost = estimateWeightCost(weight);

        long calculation = Double.valueOf(weightCost + distanceCost).longValue();

        return new Money(calculation, Currency.getInstance("UAH"));
    }

    private double estimateDistanceCost(int distanceKilometers) {
        return distanceKilometers * DISTANCE_COEFFICIENT;
    }

    private double estimateWeightCost(Double weight) {
        return weight * WEIGHT_COEFFICIENT;
    }
}
