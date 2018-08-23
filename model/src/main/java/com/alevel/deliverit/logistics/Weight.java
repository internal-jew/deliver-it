package com.alevel.deliverit.logistics;

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

    protected Weight(double weight, WeightUnit unit) {
        this.value = weight;
        this.unit = unit;
    }

    private static boolean isWeight(double weight) {
        return false;
    }
}
