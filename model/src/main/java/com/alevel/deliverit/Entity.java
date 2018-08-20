package com.alevel.deliverit;

public abstract class Entity<I extends EntityId> {
    private final I id;

    protected Entity(I id) {
        this.id = id;
    }

    public String getSimpleName() {
        return this.getClass().getSimpleName();
    }
}
