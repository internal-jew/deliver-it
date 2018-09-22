package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;

/**
 * @author Vadym Mitin
 */
public class SenderProfile {
    private final Name name;
    private final PostalAddress address;
    private final Country country;

    public SenderProfile(Name name, PostalAddress address, Country country) {
        this.name = name;
        this.address = address;
        this.country = country;
    }

    public static Parser<SenderProfile> parser() {
        return new SenderProfileParser();
    }

    public Name getName() {
        return name;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public Country getCountry() {
        return country;
    }
}
