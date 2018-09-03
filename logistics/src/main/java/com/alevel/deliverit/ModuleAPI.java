package com.alevel.deliverit;

import com.google.common.base.Preconditions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI<T extends BusinessLogicService> {
    private final T businessLogicService;

    public ModuleAPI(T businessLogicService) {
        this.businessLogicService = businessLogicService;
    }

    private Set<Method> findMethods(final Object holder, final Class<? extends Annotation> annotation) {
        final Set<Method> annotatedMethods = new LinkedHashSet<>();
        if (holder == null || annotation == null) {
            return annotatedMethods;
        } else
            for (Method method : holder.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    method.setAccessible(true);
                    annotatedMethods.add(method);
                }
            }

        return annotatedMethods;
    }
}
