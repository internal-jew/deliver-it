package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.entity.EntityId;

/**
 * @author Vadym Mitin
 */
public final class ParcelId extends EntityId<Long> {
    public ParcelId(Long value) {
        super(value);
    }

    public static Parser<ParcelId> parser() {
        return new ParcelIdParser();
    }
}
