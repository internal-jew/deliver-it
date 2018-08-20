package com.alevel.deliverit;

import java.util.InvalidPropertiesFormatException;

public class Weight {
    private final WeightUnits unit;
    private final Double value;

    public WeightUnits getUnit() {
        return unit;
    }

    public Double getValue() {
        return value;
    }

    protected Weight(Double weight, WeightUnits unit) throws InvalidPropertiesFormatException {
        if (isWeight(weight)) {
            this.value = weight;
            this.unit = unit;
        } else throw new InvalidPropertiesFormatException("Please type right weight");
    }

    private static boolean isWeight(Double weight) {
        return false;
    }
}
