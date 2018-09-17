package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Vadym Mitin
 */
public class ServiceMethod {

    private final Class methodClass;
    private final Method method;

    public ServiceMethod(Class methodClass, Method method) {
        this.methodClass = methodClass;
        this.method = method;
    }

    public <T> T invokeConsumer(Object... args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        return (T) method.invoke(methodClass.newInstance(), args);
    }

}
