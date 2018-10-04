package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.entity.EntityId;

/**
 * @author Vadym Mitin
 */
public final class SenderId extends EntityId<Long> {
    public SenderId(Long value) {
        super(value);
    }

    public static Parser<SenderId> parser() {
        return new SenderIdParser();
    }
}
