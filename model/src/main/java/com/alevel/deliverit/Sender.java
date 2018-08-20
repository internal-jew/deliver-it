package com.alevel.deliverit;

public class Sender extends Entity<SenderId> {
    private final SenderProfile senderProfile;

    protected Sender(SenderId id, SenderProfile senderProfile) {
        super(id);
        this.senderProfile = senderProfile;
    }
}
