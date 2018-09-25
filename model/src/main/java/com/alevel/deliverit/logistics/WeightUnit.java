package com.alevel.deliverit.logistics;

/**
 * @author Vadym Mitin
 */
public enum WeightUnit {
    KILOGRAM("kilogram"),
    POUND("pond");
    private final String value;

    WeightUnit(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
