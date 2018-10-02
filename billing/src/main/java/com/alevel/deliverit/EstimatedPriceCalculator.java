package com.alevel.deliverit;

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
    public static final double DISTANCE_COEFFICIENT = 49.5;

    public Money calculate(Weight parcelWeight, Route route) {
        Double weight = parcelWeight.getValue();

        //TODO implement the calculate with estimating the units
//        WeightUnit unit = parcelWeight.getUnit();

        int routeWeight = route.getWeight();

        //turning abstract route weight to concrete kilometers
        int distanceKilometers = routeWeight * 1000;

        //estimate distance cost
        double distanceCost = estimateDistanceCost(distanceKilometers);

        double weightCost = estimateWeightCost(weight);

        //TODO implement calculation with right types (like BigDecimal...)
        long calculation = Double.valueOf(weightCost + distanceCost).longValue();

        //TODO implement the calculate the cost with another currencies
        return new Money(calculation, Currency.getInstance("UAH"));
    }

    private double estimateDistanceCost(int distanceKilometers) {
        //TODO implement the calculate the cost with increasing depending on the weight
//        System.out.println();
        return distanceKilometers * DISTANCE_COEFFICIENT;
    }

    //TODO implement the calculate the cost with increasing depending on the distance
    private double estimateWeightCost(Double weight) {
        return weight * WEIGHT_COEFFICIENT;
    }
}
