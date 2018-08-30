package com.alevel.deliverit.logistics;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Класс реализующий международный стандарт заполнения почтового адреса
 * http://www.bitboost.com/ref/international-address-formats.html#Formats
 * 
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
        this.firstName = builder.firstName.toUpperCase();
        this.lastName = builder.lastName.toUpperCase();
        this.houseNumber = builder.houseNumber.toUpperCase();
        this.street = builder.street.toUpperCase();
        this.apartmentNumber = builder.apartmentNumber.toUpperCase();
        this.city = builder.city.toUpperCase();
        this.postalCode = builder.postalCode.toUpperCase();
        this.country = builder.country;
    }

    public static Builder builder() {
        return new Builder();
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
            checkNotNull(firstName);
            checkNotNull(lastName);
            checkNotNull(houseNumber);
            checkNotNull(street);
            checkNotNull(apartmentNumber);
            checkNotNull(city);
            checkNotNull(postalCode);
            checkNotNull(country);

            return new PostalAddress(this);
        }
    }
}
