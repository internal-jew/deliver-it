package com.alevel.deliverit.logistics;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Vadym Mitin
 */
public class PostalAddress {
    private final String firstName;
    private final String lastName;
    private final String houseNumber;
    private final String street;
    private final String apartmentNumber;
    private final String city;
    private final String postalCode;
    private final Country country;

    private PostalAddress(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.houseNumber = builder.houseNumber;
        this.street = builder.street;
        this.apartmentNumber = builder.apartmentNumber;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
        this.country = builder.country;

    }

    public Country getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        sb.append("\n");
        sb.append(houseNumber);
        sb.append(" ");
        sb.append(street);
        sb.append(" ");
        sb.append(apartmentNumber);
        sb.append("\n");
        sb.append(city);
        sb.append(", ");
        sb.append(postalCode);
        sb.append("\n");
        sb.append(country);

        return sb.toString();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String houseNumber;
        private String street;
        private String apartmentNumber;
        private String city;
        private String postalCode;
        private Country country;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setApartmentNumber(String apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public PostalAddress build() {
            if (Stream.of(firstName, lastName, houseNumber, street, apartmentNumber, city, postalCode, country)
                    .allMatch(Objects::nonNull)) {
                return new PostalAddress(this);
            }else throw new InvalidParameterException("some parameters lost");
        }
    }
}