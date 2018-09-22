package com.alevel.deliverit.logistics;

import com.alevel.deliverit.Parser;

/**
 * Класс хранящий в себе данные о стране
 *
 * @author Vadym Mitin
 */
public class Country {
    private final String country;
    private final String countryCode;

    public Country(String country, String countryCode) {
        this.country = country;
        this.countryCode = countryCode;
    }

    public static Parser<Country> parser() {
        return new CountryParser();
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
