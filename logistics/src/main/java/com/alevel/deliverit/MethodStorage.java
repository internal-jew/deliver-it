package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Vadym Mitin
 */
public class MethodStorage {

    private final Class methodClass;
    private final Method method;

    public MethodStorage(Class methodClass, Method method) {
        this.methodClass = methodClass;
        this.method = method;
    }

    public <T> T invokeMethod(Object... args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        return (T) method.invoke(methodClass.newInstance(), args);
    }

}
