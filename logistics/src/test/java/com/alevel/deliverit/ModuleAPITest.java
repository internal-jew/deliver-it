package com.alevel.deliverit;

import com.alevel.deliverit.moduleapi.BusinessLogicService;
import com.alevel.deliverit.moduleapi.Subscribe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vadym Mitin
 */
public class ModuleAPITest {

    @Test
    @DisplayName("check the receipt of methods")
    void checkReceipt() {

    }

    @Test
    @DisplayName("check find subscribed methods")
    void checkSubscribed() {
        BusinessLogicService businessLogicService = ModuleAPIGiven.givenSubscribe();
        ModuleAPI<BusinessLogicService> api = new ModuleAPI<>(businessLogicService);
        HashMap<String, Method> consumer = api.findSubscribedMethods();
        consumer.forEach((k, v) -> System.out.println("address = " + k + "; Method name = " + v.getName()));
    }
}
