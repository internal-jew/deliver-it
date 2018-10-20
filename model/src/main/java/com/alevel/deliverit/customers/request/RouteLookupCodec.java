package com.alevel.deliverit.customers.request;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * Sergey Bogovesov
 * Allows you to send an object on the event bus.
 */
public class RouteLookupCodec implements MessageCodec<RouteLookupRequest, RouteLookupRequest> {

    @Override
    public void encodeToWire(Buffer buffer, RouteLookupRequest request) {
    }

    @Override
    public RouteLookupRequest decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public RouteLookupRequest transform(RouteLookupRequest request) {
        return request;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        // Always -1
        return -1;
    }
}
