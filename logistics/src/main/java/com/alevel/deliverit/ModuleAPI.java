package com.alevel.deliverit;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI {
    private final Map<String, ServiceConsumer> consumersContainer = new HashMap<>();

    private ModuleAPI() {
    }

    public static ModuleAPI getInstance() {
        return Singletone.INSTANCE.instance;
    }

    /**
     * @param service An object containing annotated {@link Subscribe} annotation methods container
     */
    public <T extends BusinessLogicService> void registerConsumers(T service) {
        findAnnotatedMethods(service, consumersContainer);
    }

    /**
     * @returna Мap containing the method.invoke wrapped to {@link ServiceConsumer}
     */
    public Map<String, ServiceConsumer> getConsumersContainer() {
        return consumersContainer;
    }

    /**
     * The method looks for all functionsContainer annotated with the annotation {@link Subscribe}
     * and returns a {@link Map}, where the key is the value, and the value is the annotated method.
     *
     * @return Methods map to addresses they listen to
     */
    private <T extends BusinessLogicService> void findAnnotatedMethods(T service, Map<String, ServiceConsumer> container) {

        checkNotNull(service);

        Class<Subscribe> annotationClass = Subscribe.class;
        Class<? extends BusinessLogicService> serviceClass = service.getClass();

        for (Method method : serviceClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                String address = method.getAnnotation(annotationClass).value();
                if (container.containsKey(address)) {
                    throw new IllegalArgumentException("this address: " + address + "; already used in method: "
                            + method.getName() + "; from Class: " + service.getClass().getName());
                }
                method.setAccessible(true);
                container.put(address, message -> method.invoke(service, message));
            }
        }
    }

    private enum Singletone {
        INSTANCE;
        private final ModuleAPI instance = new ModuleAPI();
    }
}
