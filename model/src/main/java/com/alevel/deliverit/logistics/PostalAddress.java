package com.alevel.deliverit.logistics;

/**
 * @author Vadym Mitin
 */
public class PostalAddress {
    private final Country country;
    private final String address;

    public PostalAddress(Country country, String address) {
        this.country = country;
        this.address = address;
    }
}
