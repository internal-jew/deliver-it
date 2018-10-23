package com.alevel.deliverit.entity;

import java.io.Serializable;

/**
 * The base class represents the behavior of entity information storage classes
 *
 * @author Vadym Mitin
 */
public abstract class EntityId<T> implements Serializable {
    private final T value;

    public EntityId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
