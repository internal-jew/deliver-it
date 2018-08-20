package com.alevel.deliverit;

public class SenderProfile {
    private final Name name;
    private final PostalAddress address;
    private final Country country;

    public SenderProfile(Name name, PostalAddress address, Country country) {
        this.name = name;
        this.address = address;
        this.country = country;
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
