package com.alevel.deliverit.logistics;

/**
 * Класс хранящий в себе данные о стране
 *
 * @author Vadym Mitin
 */
public class Country {
    private final String country;
    private String countryCode;

    public Country(String country ) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
