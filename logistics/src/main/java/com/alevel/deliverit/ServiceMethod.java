package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author Vadym Mitin
 */
public class ServiceMethod<T> implements ServiceConsumer<T> {
    private final Class methodClass;
    private final Method method;

    public ServiceMethod(Class methodClass, Method method) {
        this.methodClass = methodClass;
        this.method = method;
    }

    @Override
    public T invokeConsumer(T... args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        return (T) method.invoke(methodClass.newInstance(), args);
    }
}
