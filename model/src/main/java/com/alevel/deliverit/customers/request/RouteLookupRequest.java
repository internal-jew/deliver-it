package com.alevel.deliverit.customers.request;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupRequest {
    private final Long beginId;
    private final Long endId;

    public RouteLookupRequest(Long beginId, Long endId) {
        this.beginId = beginId;
        this.endId = endId;
    }

    public Long getBeginId() {
        return beginId;
    }

    public Long getEndId() {
        return endId;
    }
}
