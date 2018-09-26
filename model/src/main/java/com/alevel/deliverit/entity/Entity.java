package com.alevel.deliverit.entity;

/**
 * The base class represents entity classes behavior involved in tracking
 *
 * @author Vadym Mitin
 */
public abstract class Entity<I extends EntityId> {
    private final I id;

    public Entity(I id) {
        this.id = id;
    }

    public I getId() {
        return id;
    }

    public String getName() {
        return getClass().getSimpleName();
    }
}
