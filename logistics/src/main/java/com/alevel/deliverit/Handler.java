package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Vadym Mitin
 */
public interface Handler<T> {
    void invokeMethod(T t) throws InvocationTargetException, IllegalAccessException;
}
