package com.alevel.deliverit.logistics;

/**
 * @author Vadym Mitin
 */
public enum WeightUnit {
    KILOGRAM("kilogram"),
    POUND("pond");
    public final String value;

    WeightUnit(String value) {
        this.value = value;
    }
}
