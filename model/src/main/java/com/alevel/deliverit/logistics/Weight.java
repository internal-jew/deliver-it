package com.alevel.deliverit.logistics;

import com.alevel.deliverit.Parser;

/**
 * @author Vadym Mitin
 */
public class Weight {
    private final WeightUnit unit;
    private final double value;

    public WeightUnit getUnit() {
        return unit;
    }

    public Double getValue() {
        return value;
    }

    public Weight(double weight, WeightUnit unit) {
        this.value = weight;
        this.unit = unit;
    }

    public static Parser<Weight> parser() {
        return new WeightParser();
    }

    private static boolean isWeight(double weight) {
        return false;
    }
}
