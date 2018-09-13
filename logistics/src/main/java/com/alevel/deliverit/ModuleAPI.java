package com.alevel.deliverit;


import com.google.common.base.Preconditions;
import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI {
    private final Map<String, MethodStorage> methodsContainer = new HashMap<>();
    private final Map<String, Handler> methods = new HashMap<>();

    private ModuleAPI() {
    }

    public static ModuleAPI getInstance() {
        return Single.INSTANCE.instance;
    }

    /**
     * @param service An object containing annotated {@link Subscribe} annotation methodsContainer
     */
    public <T extends BusinessLogicService> void register(T service) throws IllegalAccessException,
            IllegalAnnotationException, InstantiationException {
        findAnnotatedMethods(service);
    }

    /**
     * @return a Мap containing the {@link MethodStorage}
     */
    public Map<String, MethodStorage> getMethodsContainer() {
        return methodsContainer;
    }

    /**
     * @returna Мap containing the method.invoke wrapped to {@link Handler}
     */
    public Map<String, Handler> getFunctionContainer() {
        return methods;
    }

    /**
     * The method looks for all methodsContainer annotated with the annotation {@link Subscribe}
     * and returns a Hash Map, where the key is the value, and the value is the annotated method.
     *
     * @return Methods map to addresses they listen to
     */
    private <T extends BusinessLogicService> void findAnnotatedMethods(T service) throws IllegalAccessException,
            InstantiationException, IllegalAnnotationException {

        Preconditions.checkNotNull(service);

        Class<Subscribe> annotationClass = Subscribe.class;
        Class<? extends BusinessLogicService> serviceClass = service.getClass();

        for (Method method : serviceClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                String address = method.getAnnotation(annotationClass).value();
                if (methodsContainer.containsKey(address)) {
                    throw new IllegalStateException("this address: " + address + "; already used in method: "
                            + method.getName() + "; from Class: " + service.getClass().getName());
                }
                method.setAccessible(true);
                methodsContainer.put(address, new MethodStorage(serviceClass, method));
                methods.put(address, new Handler() {
                    @Override
                    public Object invokeMethod(Object o) throws InvocationTargetException, IllegalAccessException {
                        return method.invoke(service, o);
                    }
                });
            }
        }
    }

    private enum Single {
        INSTANCE;
        private final ModuleAPI instance = new ModuleAPI();
    }
}
