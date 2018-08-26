package com.alevel.deliverit.logistics.fsm;

public class CommandDoesNotExists extends Exception {
    public CommandDoesNotExists(String message) {
        super(message);
    }

    public CommandDoesNotExists() {
    }
}