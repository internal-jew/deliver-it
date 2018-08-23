package com.alevel.deliverit.entity;

/**
 * Базовый класс определяющий поведение классов хранилих информации о сущностях.
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
