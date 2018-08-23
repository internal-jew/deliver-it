package com.alevel.deliverit.logistics;
/*
Класс хранящий в себе данные о стране
 */
public class Country {
    private final String country;
    private final String countryCode;

    public Country(String country, String countryCode) {
        this.country = country;
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
