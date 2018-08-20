package com.alevel.deliverit;

/**
 *
 * @param <I>
 */

public abstract class Entity<I extends EntityId> {
    private final I id;

    protected Entity(I id) {
        this.id = id;
    }

    public String getName() {
        return getClass().getSimpleName();
    }
}
