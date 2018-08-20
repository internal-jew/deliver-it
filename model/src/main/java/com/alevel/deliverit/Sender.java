package com.alevel.deliverit;

public class Sender extends Entity {
    private final SenderProfile senderProfile;

    protected Sender(EntityId id, SenderProfile senderProfile) {
        super(id);
        this.senderProfile = senderProfile;
    }
}
