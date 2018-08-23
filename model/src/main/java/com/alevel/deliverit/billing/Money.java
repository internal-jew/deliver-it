package com.alevel.deliverit.billing;

import java.util.Currency;

/**
 * @author Vadym Mitin
 */
public class Money {
    private final int value;
    private final Currency currency;

    public Money(int value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
