package com.alevel.deliverit.customers.request;

import java.io.Serializable;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupRequest implements Serializable {
    private final Long start;
    private final Long finish;

    public RouteLookupRequest(Long startId, Long finishId) {
        this.start = startId;
        this.finish = finishId;
    }

    public Long getStart() {
        return start;
    }

    public Long getFinish() {
        return finish;
    }
}
