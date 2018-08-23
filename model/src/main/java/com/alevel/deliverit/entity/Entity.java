package com.alevel.deliverit.entity;

/**
 * Базовый класс определяющий поведение классов сущностей,
 * учавствующих в процессе трекинга
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
