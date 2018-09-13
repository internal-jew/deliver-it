package com.alevel.deliverit;

import io.vertx.core.eventbus.Message;

/**
 * @author Vadym Mitin
 */
public class ModuleAPIGiven {

    public static BusinessLogicService givenSubscribe() {

        BusinessLogicService businessLogicService = new BusinessLogicService() {
            @Subscribe(value = "value 1")
            public String stringMethod() {
                return "Some Method 1 return";
            }

            @Subscribe(value = "value 2")
            public int intMethod() {
                return 10;
            }

            @Subscribe(value = "value 3")
            public Message<String> nullMessageMethod() {
                return null;
            }
        };
        return businessLogicService;
    }
    public static class TestClass implements BusinessLogicService{
        @Subscribe(value = "value 4")
        public String stringMethod() {
            return "Some Method 1 return";
        }

        @Subscribe(value = "value 5")
        public int intMethod() {
            return 10;
        }

        @Subscribe(value = "value 6")
        public Message<String> nullMessageMethod() {
            return null;
        }
    }
}
