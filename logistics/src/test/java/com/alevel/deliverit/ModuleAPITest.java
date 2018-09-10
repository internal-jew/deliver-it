package com.alevel.deliverit;

import com.alevel.deliverit.moduleapi.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Vadym Mitin
 */
public class ModuleAPITest {

    @Test
    @DisplayName("check find subscribed methods")
    void checkSubscribed() {
        BusinessLogicService businessLogicService = ModuleAPIGiven.givenSubscribe();
        ModuleAPI<BusinessLogicService> api = new ModuleAPI<>(businessLogicService);
        HashMap<String, Method> consumer = api.findSubscribedMethods();
        consumer.forEach((k, v) -> System.out.println("address = " + k + "; Method name = " + v.getName()));
    }

    @Test
    @DisplayName("check find subscribed methods 2")
    void checkSubscribed2() {
        ModuleAPIGiven.TestClass testClass = new ModuleAPIGiven.TestClass();
        ModuleAPI<BusinessLogicService> api = new ModuleAPI<>(testClass);
        HashMap<String, Method> consumer = api.findSubscribedMethods();
        consumer.forEach((k, v) -> System.out.println("address = " + k + "; Method name = " + v.getName()));
    }
}
