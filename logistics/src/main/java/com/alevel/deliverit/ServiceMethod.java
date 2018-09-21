package com.alevel.deliverit;

import org.checkerframework.checker.nullness.Opt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

/**
 * @author Vadym Mitin
 */
public class ServiceMethod<S> {
    private final S service;
    private final Method method;

    public ServiceMethod(S service, Method method) {
        this.service = service;
        this.method = method;
    }

    public Optional invokeConsumer(Object... args) throws InvocationTargetException, IllegalAccessException {
        return ofNullable(method.invoke(service, args));
    }
}
