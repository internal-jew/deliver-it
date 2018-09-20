package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Vadym Mitin
 */
public interface ServiceConsumer<T> {
    T invokeConsumer(T... args) throws InvocationTargetException, IllegalAccessException, InstantiationException;
}
