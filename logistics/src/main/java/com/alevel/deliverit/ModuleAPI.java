package com.alevel.deliverit;


import com.alevel.deliverit.moduleapi.BusinessLogicService;
import com.alevel.deliverit.moduleapi.Subscribe;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vadym Mitin
 */
public class ModuleAPI<T extends BusinessLogicService> {
    private final Map<String, Method> methods = new HashMap<>();

    private ModuleAPI() {
    }

    public void register(T businessLogicService){
        findSubscribedMethods(businessLogicService);
    }

    public Map<String, Method> getMethods() {
        return methods;
    }

    /**
     * The method looks for all methods annotated with the annotation {@link Subscribe}
     * and returns a Hash Map, where the key is the address, and the value is the annotated method.
     *
     * @return Methods maped to addresses they listen to
     */
    private void findSubscribedMethods(T businessLogicService) {
        Class<Subscribe> annotation = Subscribe.class;
        if (businessLogicService == null ) {
        } else
            for (Method method : businessLogicService.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    method.setAccessible(true);
                    String topic = method.getAnnotation(annotation).address();
                    methods.put(topic, method);
                }
            }
    }
    private static enum Single{
        INSTANCE;
        private final ModuleAPI instance = new ModuleAPI();
    }
}
