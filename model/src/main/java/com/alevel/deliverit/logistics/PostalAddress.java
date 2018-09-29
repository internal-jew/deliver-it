package com.alevel.deliverit.logistics;

import com.alevel.deliverit.Parser;

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

    public static Parser<PostalAddress> parser() {
        return new PostalAddressParser();
    }
}
