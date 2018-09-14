package com.alevel.deliverit;

import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Vadym Mitin
 */
@DisplayName("Module Api should")
public class ModuleAPITest {

    @Test
    @DisplayName("find subscribed methods without parameters")
    void checkMethodContainer() throws InvocationTargetException,
            IllegalAccessException, InstantiationException, IllegalAnnotationException {

        ModuleAPIGiven.TestClass1 testClass1 = new ModuleAPIGiven.TestClass1();
        ModuleAPI api = ModuleAPI.getInstance();

        api.register(testClass1);

//        Map<String, Consumer> functionContainer = api.getFunctionContainer();
        Map<String, MethodStorage> methodsContainer = api.getMethodsContainer();

        for (Map.Entry<String, MethodStorage> e : methodsContainer.entrySet()) {
            MethodStorage value = e.getValue();
            String address = e.getKey();
            if (address.equals("value 1")) {
                assertEquals("Some Method 1 return", value.invokeMethod());
            }
            if (address.equals("value 2")) {
                assertEquals(10, java.util.Optional.ofNullable(value.invokeMethod()).get());
            }
            if (address.equals("value 3")) {
                assertNull(value.invokeMethod());
            }
            if (address.equals("value 4")) {
                value.invokeMethod();
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

        Map<String, MethodStorage> methodsContainer = api.getMethodsContainer();

        for (Map.Entry<String, MethodStorage> e : methodsContainer.entrySet()) {
            String address = e.getKey();
            MethodStorage value = e.getValue();

            if (address.equals("Str")) {
                String s = "Hello";
                Object invokeMethod = value.invokeMethod(s);
                Assertions.assertEquals(s, invokeMethod);
                System.out.println(invokeMethod);
            }
            if (address.equals("Int")) {
                int i = 10;
                Object invokeMethod = value.invokeMethod(10);
                assertEquals(i, invokeMethod);
                System.out.println(invokeMethod);
            }
            if (address.equals("Bool")) {
                boolean b = true;
                boolean invokeMethod = (boolean) value.invokeMethod(b);
                Assertions.assertTrue(invokeMethod);
                System.out.println(invokeMethod);
            }
        }
    }

    @Test
    @DisplayName("run vertx witch MethodStorage")
    void checkVertx() {

        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        ModuleAPIGiven.TestClass4 testClass4 = new ModuleAPIGiven.TestClass4();
        ModuleAPI api = ModuleAPI.getInstance();

        api.register(testClass4);

        Map<String, MethodStorage> methodsStorage = api.getMethodsContainer();
        for (Map.Entry<String, MethodStorage> e : methodsStorage.entrySet()) {
            String address = e.getKey();
            MethodStorage value = e.getValue();
            eb.consumer(address, message -> {
                try {
                    value.invokeMethod(message.body());
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
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

        Map<String, Handler> methodsContainer = api.getFunctionContainer();
        for (Map.Entry<String, Handler> e : methodsContainer.entrySet()) {
            String address = e.getKey();
            Handler value = e.getValue();
            eb.consumer(address, message -> {
                try {
                    value.invokeMethod(message.body());
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
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
