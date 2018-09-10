package com.alevel.deliverit.moduleapi;

/**
 * @author Vadym Mitin
 */
public class MethodStorage {
    private final String address;
    private final Object message;

    public MethodStorage(String address, Object message) {
        this.address = address;
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public Object getMessage() {
        return message;
    }
}
