package com.alevel.deliverit.customers;

import com.alevel.deliverit.entity.EntityId;

/**
 * @author Vadym Mitin
 */
public final class SenderId extends EntityId<Long> {
    protected SenderId(Long value) {
        super(value);
    }
}
