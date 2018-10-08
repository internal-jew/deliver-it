package com.alevel.deliverit.entity;

/**
 * The base class represents the behavior of entity information storage classes
 *
 * @author Vadym Mitin
 */
public abstract class EntityId<T> {
    private final T value;

    public EntityId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
