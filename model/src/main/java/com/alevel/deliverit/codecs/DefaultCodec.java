package com.alevel.deliverit.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * @author Sergey Bogovesov
 * Allows to send an object on the event bus.
 */
public class DefaultCodec implements MessageCodec<Object, Object> {
    @Override
    public void encodeToWire(Buffer buffer, Object money) {

    }

    @Override
    public Object decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public Object transform(Object money) {
        return money;
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
