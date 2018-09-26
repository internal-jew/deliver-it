package com.alevel.deliverit.entity;

/**
 * Базовый класс определяющий поведение классов хранилих информации о сущностях.
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
