package com.alevel.deliverit.logistics.postal.network;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.entity.EntityId;

public final class PostOfficeId extends EntityId<Long> {

    public PostOfficeId(long value) {
        super(value);
    }

    public static Parser<PostOfficeId> parser() {
        return new PostOfficeIdParser();
    }
}
