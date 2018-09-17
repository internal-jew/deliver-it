package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Vadym Mitin
 */
public interface ServiceConsumer<T> {
    void invokeConsumer(T t) throws InvocationTargetException, IllegalAccessException;
}
