package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Vadym Mitin
 */
public interface Handler<T> {
    <A> A invokeMethod(T t) throws InvocationTargetException, IllegalAccessException;
}
