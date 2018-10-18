package com.alevel.deliverit.customers.request;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupRequest  {
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
