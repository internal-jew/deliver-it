package com.alevel.deliverit.logistics;

/**
 * Класс обертка, хранящий в себе данные о стране.
 *
 * @author Vadym Mitin
 */
public class Country {
    private final String country;
    private String countryCode;

    public Country(String country ) {
        this.country = country.toUpperCase();
    }

    public Country(String country, String countryCode) {
        this.country = country.toUpperCase();
        this.countryCode = countryCode.toUpperCase();
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
