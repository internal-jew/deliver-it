package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Vadym Mitin
 */
public interface Handler<T> {
     void handle(T t) throws InvocationTargetException, IllegalAccessException;
}
