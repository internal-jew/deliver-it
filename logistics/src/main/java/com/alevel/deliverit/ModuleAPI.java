package com.alevel.deliverit;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI<T extends BusinessLogicService> {
    private final T businessLogicService;

    public ModuleAPI(T businessLogicService) {
        this.businessLogicService = businessLogicService;
    }

    private HashMap<String, Method> findSubscribedMethods(final Object methodHolder, final Class<Subscribe> annotation) {
        final HashMap<String, Method> annotatedMethods = new HashMap<>();
        if (methodHolder == null || annotation == null) {
            return annotatedMethods;
        } else
            for (Method method : methodHolder.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    method.setAccessible(true);
                    String topic = method.getAnnotation(annotation).topic();
                    annotatedMethods.put(topic, method);
                }
            }
        return annotatedMethods;
    }

    public MethodStorage<A> getConsumer() {
        String address =
        return new MethodStorage<A>()
    }
}
