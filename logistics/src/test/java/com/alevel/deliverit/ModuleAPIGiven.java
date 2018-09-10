package com.alevel.deliverit;

import com.alevel.deliverit.moduleapi.*;
import io.vertx.core.eventbus.Message;

/**
 * @author Vadym Mitin
 */
public class ModuleAPIGiven {

    public static BusinessLogicService givenSubscribe() {

        BusinessLogicService businessLogicService = new BusinessLogicService() {
            @Subscribe(topic = "address 1")
            public String stringMethod() {
                return "Some Method 1 return";
            }

            @Subscribe(topic = "address 2")
            public int intMethod() {
                return 10;
            }

            @Subscribe(topic = "address 3")
            public Message<String> nullMessageMethod() {
                return null;
            }
        };
        return businessLogicService;
    }
    public static class TestClass implements BusinessLogicService{
        @Subscribe(topic = "address 4")
        public String stringMethod() {
            return "Some Method 1 return";
        }

        @Subscribe(topic = "address 5")
        public int intMethod() {
            return 10;
        }

        @Subscribe(topic = "address 6")
        public Message<String> nullMessageMethod() {
            return null;
        }
    }
}
