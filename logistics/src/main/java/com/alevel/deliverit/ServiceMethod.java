package com.alevel.deliverit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

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

    public Optional invokeConsumer(Object... args) {
        try {
            if (Void.TYPE.equals(method.getReturnType())) {
                return empty();
            } else {
                return of(method.invoke(service, args));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Error occurred when calling a @Subscribe method");
        } catch (Exception e) {
            throw e;
        }
    }
}
