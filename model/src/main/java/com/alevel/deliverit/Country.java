package com.alevel.deliverit;

import java.util.Locale;

public class Country {
    private final String country;
    private final String countryCode;

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Country(String country, String countryCode) {
        this.country = country;
        this.countryCode = countryCode;
    }

    public static void main(String[] args) {
        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {
            Locale country = new Locale("", countryCode);
            System.out.println("Country Code = " + country.getCountry()
                    + ", Country Name = " + country.getDisplayCountry());
        }
        System.out.println("Done");
    }

}
