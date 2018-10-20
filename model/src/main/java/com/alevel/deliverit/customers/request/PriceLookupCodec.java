package com.alevel.deliverit.customers.request;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * @author Sergey Bogovesov
 * Allows you to send an object on the event bus.
 */
public class PriceLookupCodec implements MessageCodec<PriceLookupRequest, PriceLookupRequest> {

    @Override
    public void encodeToWire(Buffer buffer, PriceLookupRequest request) {
    }

    @Override
    public PriceLookupRequest decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public PriceLookupRequest transform(PriceLookupRequest request) {
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
