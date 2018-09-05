package com.alevel.deliverit.customers;

import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;

/**
 * @author Vadym Mitin
 */
public class SenderProfile {
    private final Name senderName;
    private final PostalAddress address;
    private final Country country;

    public SenderProfile(Name senderName, PostalAddress address, Country country) {
        this.senderName = senderName;
        this.address = address;
        this.country = country;
    }

    public Name getSenderName() {
        return senderName;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public Country getCountry() {
        return country;
    }
}
