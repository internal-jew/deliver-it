package com.alevel.deliverit;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 * @author Vadym Mitin
 */
public class ModuleAPI {
    private static final Class<Subscribe> ANNOTATION_CLASS = Subscribe.class;

    private final Map<String, ServiceMethod> consumersContainer = new HashMap<>();

    private ModuleAPI() {
    }

    public static ModuleAPI getInstance() {
        return Singletone.INSTANCE.instance;
    }

    /**
     * @param service an object containing annotated with {@link Subscribe} annotation methods container
     */
    public <T extends BusinessLogicService> void registerConsumers(T service) {
        findAnnotatedMethods(service, consumersContainer);
    }

    /**
     * @return a {@link Map} containing the {@link Method#invoke(Object, Object...)} wrapped to {@link ServiceMethod}
     */
    public Map<String, ServiceMethod> getConsumersContainer() {
        return consumersContainer;
    }

    /**
     * The method looks for all methods in the registered {@link #registerConsumers(BusinessLogicService)}
     * annotated {@link Subscribe} and returns {@link Map}, where the key is the value,
     * and the value is the annotated method.
     *
     * @return Methods map to addresses they listen to
     */
    private <T extends BusinessLogicService> void findAnnotatedMethods(T service, Map<String, ServiceMethod> container) {

        checkNotNull(service);

        Class<? extends BusinessLogicService> serviceClass = service.getClass();

        for (Method method : serviceClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ANNOTATION_CLASS)) {
                String address = method.getAnnotation(ANNOTATION_CLASS).value();
                if (container.containsKey(address)) {
                    throw new IllegalArgumentException(format(" this address: %s ; already used in method: %s ; from Class: %s"
                            , address
                            , method.getName()
                            , serviceClass.getName()));
                }
                method.setAccessible(true);
                container.put(address, new ServiceMethod(service, method));
            }
        }
    }

    private enum Singletone {
        INSTANCE;
        private final ModuleAPI instance = new ModuleAPI();
    }
}
