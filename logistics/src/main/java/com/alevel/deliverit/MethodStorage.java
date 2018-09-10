package com.alevel.deliverit;


import io.vertx.core.Handler;

public class MethodStorage<A> {
    private final String address;
    private final Handler<A> handler;

    public MethodStorage(String address, Handler<A> handler) {
        this.address = address;
        this.handler = handler;
    }

    public String getAddress() {
        return address;
    }

    public Handler<A> getHandler() {
        return handler;
    }
}
