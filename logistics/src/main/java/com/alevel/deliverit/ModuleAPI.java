package com.alevel.deliverit;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI {
    private final Map<String, ServiceMethod> functionsContainer = new HashMap<>();
    private final Map<String, ServiceConsumer> consumersContainer = new HashMap<>();

    private ModuleAPI() {
    }

    public static ModuleAPI getInstance() {
        return Singletone.INSTANCE.instance;
    }

    /**
     * @param service An object containing annotated {@link Subscribe} annotation methods container
     */
    public <T extends BusinessLogicService> void register(T service) {
        findAnnotatedMethods(service);
    }

    public <T extends BusinessLogicService> void registerFunction(T service) {
        findAnnotatedFunction(service);
    }

    /**
     * @return a Мap containing the {@link ServiceMethod}
     */
    public Map<String, ServiceMethod> getFunctionsContainer() {
        return functionsContainer;
    }

    /**
     * @returna Мap containing the method.invoke wrapped to {@link ServiceConsumer}
     */
    public Map<String, ServiceConsumer> getConsumersContainer() {
        return consumersContainer;
    }

    // Map<String, ServiceConsumer> consumersContainer

    /**
     * The method looks for all functionsContainer annotated with the annotation {@link Subscribe}
     * and returns a Hash Map, where the key is the value, and the value is the annotated method.
     *
     * @return Methods map to addresses they listen to
     */
    private <T extends BusinessLogicService, K, V> void findAnnotatedMethods(T service) {

        checkNotNull(service);

        Class<Subscribe> annotationClass = Subscribe.class;
        Class<? extends BusinessLogicService> serviceClass = service.getClass();

        for (Method method : serviceClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                String address = method.getAnnotation(annotationClass).value();
                if (functionsContainer.containsKey(address)) {
                    throw new IllegalArgumentException("this address: " + address + "; already used in method: "
                            + method.getName() + "; from Class: " + service.getClass().getName());
                }
                method.setAccessible(true);
                functionsContainer.put(address, new ServiceMethod(serviceClass, method));
            }
        }
    }

    private <T extends BusinessLogicService> void findAnnotatedFunction(T service) {

        checkNotNull(service);

        Class<Subscribe> annotationClass = Subscribe.class;
        Class<? extends BusinessLogicService> serviceClass = service.getClass();

        for (Method method : serviceClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                String address = method.getAnnotation(annotationClass).value();
                if (consumersContainer.containsKey(address)) {
                    throw new IllegalArgumentException("this address: " + address + "; already used in method: "
                            + method.getName() + "; from Class: " + service.getClass().getName());
                }
                method.setAccessible(true);
                consumersContainer.put(address, message -> method.invoke(service, message));
            }
        }
    }

    private enum Singletone {
        INSTANCE;
        private final ModuleAPI instance = new ModuleAPI();
    }
}
