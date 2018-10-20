package com.alevel.deliverit.billing;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * @author Sergey Bogovesov
 * Allows you to send an object on the event bus.
 */
public class MoneyCodec implements MessageCodec<Money, Money> {
    @Override
    public void encodeToWire(Buffer buffer, Money money) {

    }

    @Override
    public Money decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public Money transform(Money money) {
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
