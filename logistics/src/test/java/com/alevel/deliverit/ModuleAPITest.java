package com.alevel.deliverit;

import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
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
    @DisplayName("check find subscribed methods without parameters")
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

//    @Test
//    @DisplayName("check find subscribed methods with parameters")
//    void checkFunctionContainer() throws InvocationTargetException,
//            IllegalAccessException, InstantiationException, IllegalAnnotationException {
//
//        ModuleAPIGiven.TestClass2 testClass2 = new ModuleAPIGiven.TestClass2();
//        ModuleAPI api = ModuleAPI.getInstance();
//
//        api.register(testClass2);
//
//        Map<String, Handler> methodsContainer = api.getFunctionContainer();
////        Map<String, MethodStorage> methodsContainer = api.getMethodsContainer();
//
//        for (Map.Entry<String, Handler> e : methodsContainer.entrySet()) {
//            String address = e.getKey();
//            Handler value = e.getValue();
//
//            if (address.equals("Str")) {
//                String s = "Hello";
//                Object handle = value.handle(s);
//                Assertions.assertEquals(s, handle);
//                System.out.println(handle);
//            }
//            if (address.equals("Int")) {
//                int i = 10;
//                Object handle = value.handle(10);
//                assertEquals(i, handle);
//                System.out.println(handle);
//            }
//            if (address.equals("Bool")) {
//                boolean b = true;
//                boolean handle = (boolean) value.handle(b);
//                Assertions.assertTrue(handle);
//                System.out.println(handle);
//            }
//        }
//    }

    @Test
    @DisplayName("Check vertx handler")
    void checkVertx() throws IllegalAccessException, InstantiationException,
            IllegalAnnotationException, InvocationTargetException {

        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        ModuleAPIGiven.TestClass4 testClass4 = new ModuleAPIGiven.TestClass4();
        ModuleAPI api = ModuleAPI.getInstance();

        api.register(testClass4);

        Map<String, Handler> methodsContainer = api.getFunctionContainer();
        Map<String, MethodStorage> storageContainer = api.getMethodsContainer();

        for (Map.Entry<String, Handler> e : methodsContainer.entrySet()) {
            String address = e.getKey();
            Handler value = e.getValue();
            eb.consumer(address, message -> {
                try {
                    value.handle(message.body());
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            });
        }
        eb.send("address.1", "Hello");
        eb.send("address.2", 100);
    }
}
