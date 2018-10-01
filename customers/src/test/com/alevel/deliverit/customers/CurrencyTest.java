package com.alevel.deliverit.customers;

import org.junit.jupiter.api.Test;

import java.util.Currency;

/**
 * @author Vadym Mitin
 */
public class CurrencyTest {
    @Test
    void currencies() {
        Currency.getAvailableCurrencies().stream().forEach(System.out::println);
    }
}
