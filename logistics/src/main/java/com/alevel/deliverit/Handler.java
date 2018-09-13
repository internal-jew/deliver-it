package com.alevel.deliverit;

/**
 * @author Vadym Mitin
 */
public interface Handler<T> {
    T handle(T t);
}
