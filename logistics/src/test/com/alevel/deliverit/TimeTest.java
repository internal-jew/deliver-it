package com.alevel.deliverit;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Vadym Mitin
 */
public class TimeTest {
    @Test
    void timeTest() {
        LocalDate now = LocalDate.now();

        Month month = now.getMonth();
        int value = month.getValue();
        int dayOfMonth = now.getDayOfMonth();
        int year = now.getYear();
        System.out.println(String.format("%s \\ %s \\ %s", dayOfMonth, value, year));

        LocalDate localDate = now.plusDays(20);

        value = localDate.getMonth().getValue();
        dayOfMonth = localDate.getDayOfMonth();
        year = localDate.getYear();

        System.out.println(String.format("%s \\ %s \\ %s", dayOfMonth, value, year));

    }
}
