package com.alevel.deliverit.billing;

import java.util.Currency;

/**
 * @author Vadym Mitin
 */
public class Money {
    private final long value;
    private final Currency currency;

    public Money(long value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public long getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
