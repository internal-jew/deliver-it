package com.alevel.deliverit.customers;

//import com.alevel.deliverit.customers.Parser;
//import com.alevel.deliverit.customers.SenderParser;

import com.alevel.deliverit.entity.Entity;

/**
 * @author Vadym Mitin
 */
public class Sender extends Entity<SenderId> {
    private final SenderProfile senderProfile;

    public static Parser parser() {
        return new SenderParser();
    }

    public Sender(SenderId id, SenderProfile senderProfile) {
        super(id);
        this.senderProfile = senderProfile;
    }
}
