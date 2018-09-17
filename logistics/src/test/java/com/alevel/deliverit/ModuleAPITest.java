package com.alevel.deliverit;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Vadym Mitin
 */
@DisplayName("Module Api should")
public class ModuleAPITest {

    public static final String SOME_METHOD_1_RETURN = "Some Method 1 return";

    @Test
    @DisplayName("find subscribed methods without parameters")
    void checkMethodContainer() throws InvocationTargetException,
            IllegalAccessException, InstantiationException {

        ModuleAPIGiven.TestClass1 testClass1 = new ModuleAPIGiven.TestClass1();
        ModuleAPI api = ModuleAPI.getInstance();

        api.register(testClass1);

        Map<String, ServiceMethod> methodsContainer = api.getFunctionsContainer();

        for (Map.Entry<String, ServiceMethod> e : methodsContainer.entrySet()) {
            ServiceMethod value = e.getValue();
            String address = e.getKey();
            if (address.equals("value 1")) {
                assertEquals(SOME_METHOD_1_RETURN, value.invokeConsumer());
            }
            if (address.equals("value 2")) {
                assertEquals(10, java.util.Optional.ofNullable(value.invokeConsumer()).get());
            }
            if (address.equals("value 3")) {
                assertNull(value.invokeConsumer());
            }
            if (address.equals("value 4")) {
                value.invokeConsumer();
            }
        }
    }

    @Test
    @DisplayName("find subscribed methods with parameters")
    void checkFunctionContainer() throws InvocationTargetException,
            IllegalAccessException, InstantiationException {

        ModuleAPIGiven.TestClass2 testClass2 = new ModuleAPIGiven.TestClass2();
        ModuleAPI api = ModuleAPI.getInstance();

        api.register(testClass2);

        Map<String, ServiceMethod> methodsContainer = api.getFunctionsContainer();

        for (Map.Entry<String, ServiceMethod> e : methodsContainer.entrySet()) {
            String address = e.getKey();
            ServiceMethod value = e.getValue();

            if (address.equals("Str")) {
                Object invokeMethod = value.invokeConsumer(SOME_METHOD_1_RETURN);
                assertEquals(SOME_METHOD_1_RETURN, invokeMethod);
                System.out.println(invokeMethod);
            }
            if (address.equals("Int")) {
                int i = 10;
                Object invokeMethod = value.invokeConsumer(10);
                assertEquals(i, invokeMethod);
                System.out.println(invokeMethod);
            }
            if (address.equals("Bool")) {
                boolean b = true;
                boolean invokeMethod = (boolean) value.invokeConsumer(b);
                assertTrue(invokeMethod);
                System.out.println(invokeMethod);
            }
        }
    }

    @Test
    @DisplayName("run vertx witch ServiceMethod")
    void checkVertx() {

        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        ModuleAPIGiven.TestClass4 testClass4 = new ModuleAPIGiven.TestClass4();
        ModuleAPI api = ModuleAPI.getInstance();

        api.register(testClass4);

        Map<String, ServiceMethod> methodsStorage = api.getFunctionsContainer();

        for (Map.Entry<String, ServiceMethod> e : methodsStorage.entrySet()) {
            String address = e.getKey();
            ServiceMethod value = e.getValue();
            eb.consumer(address, message -> {
                try {
                    value.invokeConsumer(message.body());
                } catch (InvocationTargetException | IllegalAccessException | InstantiationException e1) {
                    e1.printStackTrace();
                }
            });
        }

        eb.send("address.1", "Hello address.1");
        eb.send("address.2", new Double(10));
        eb.send("address.1", null);
        eb.send("address.2", null);
    }

    @Test
    @DisplayName("run vertx witch function")
    void checkVertx2() {

        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        ModuleAPIGiven.TestClass5 testClass5 = new ModuleAPIGiven.TestClass5();
        ModuleAPI api = ModuleAPI.getInstance();

        api.registerFunction(testClass5);

        Map<String, ServiceConsumer> methodsContainer = api.getConsumersContainer();

        for (Map.Entry<String, ServiceConsumer> e : methodsContainer.entrySet()) {
            String address = e.getKey();
            ServiceConsumer value = e.getValue();
            eb.consumer(address, message -> {
                try {
                    value.invokeConsumer(message.body());
                } catch (InvocationTargetException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            });
        }

        eb.send("address.3", "Hello address.3");
        eb.send("address.4", new Double(10));
        eb.send("address.3", null);
        eb.send("address.4", null);
    }
}
