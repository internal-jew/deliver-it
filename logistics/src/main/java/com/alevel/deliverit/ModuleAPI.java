package com.alevel.deliverit;


import com.alevel.deliverit.moduleapi.BusinessLogicService;
import com.alevel.deliverit.moduleapi.Subscribe;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI<T extends BusinessLogicService> {
    private final T businessLogicService;
    private final HashMap<String, Method> methods;

    public ModuleAPI(T businessLogicService) {
        this.businessLogicService = businessLogicService;
        this.methods = new HashMap<>();
    }

    public HashMap<String, Method> findSubscribedMethods() {
        Class<Subscribe> annotation = Subscribe.class;
        if (businessLogicService == null ) {
            return methods;
        } else
            for (Method method : businessLogicService.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    method.setAccessible(true);
                    String topic = method.getAnnotation(annotation).topic();
                    methods.put(topic, method);
                }
            }
        return methods;
    }

}
