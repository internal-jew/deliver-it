package com.alevel.deliverit.logistics.postal.network;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * Sergey Bogovesov
 */
public class RouteCodec implements MessageCodec<Route, Route> {
    @Override
    public void encodeToWire(Buffer buffer, Route route) {

    }

    @Override
    public Route decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public Route transform(Route route) {
        return route;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
