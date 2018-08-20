package com.alevel.deliverit;

/**
 * Hello world!
 */
public abstract class EntityId<T> {
    private final T value;

    protected EntityId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
