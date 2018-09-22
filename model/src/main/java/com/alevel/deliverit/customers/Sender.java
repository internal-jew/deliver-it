package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.entity.Entity;

/**
 * @author Vadym Mitin
 */
public class Sender extends Entity<SenderId> {
    private final SenderProfile senderProfile;

    public Sender(SenderId id, SenderProfile senderProfile) {
        super(id);
        this.senderProfile = senderProfile;
    }

    public static Parser<Sender> parser() {
        return new SenderParser();
    }
}
