package com.alevel.deliverit;


import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI<T extends BusinessLogicService> {
    private final Map<String, MethodStorage> methodsContainer = new HashMap<>();

    private ModuleAPI() {
    }

    public static <T> ModuleAPI getInstance() {
        return Single.INSTANCE.instance;
    }

    /**
     * @param businessLogicService An object containing annotated {@link Subscribe} annotation methodsContainer
     */
    public void register(T businessLogicService) throws IllegalAccessException, IllegalAnnotationException, InstantiationException {
        findSubscribedMethods(businessLogicService);
    }

    public Map<String, MethodStorage> getMethodsContainer() {
        return methodsContainer;
    }

    /**
     * The method looks for all methodsContainer annotated with the annotation {@link Subscribe}
     * and returns a Hash Map, where the key is the value, and the value is the annotated method.
     *
     * @return Methods map to addresses they listen to
     */
    private void findSubscribedMethods(T service) throws IllegalAccessException, InstantiationException, IllegalAnnotationException {
        Class<Subscribe> annotation = Subscribe.class;
        if (service == null) {
            for (Method method : service.getClass().getDeclaredMethods()) {
                String address = method.getAnnotation(annotation).value();
                if (methodsContainer.containsKey(address)) {
                    throw new IllegalAnnotationException("this value already used", annotation.newInstance());
                }
                if (method.isAnnotationPresent(annotation)) {
                    method.setAccessible(true);
                    methodsContainer.put(address, new MethodStorage(service.getClass(), method));
                }
            }
        }
    }


    private static enum Single {
        INSTANCE;
        private final ModuleAPI instance = new ModuleAPI();
    }
}
