package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.BusinessLogicService;
import com.alevel.deliverit.Subscribe;
import io.vertx.core.eventbus.Message;

/**
 * @author Vadym Mitin
 */
public class ModuleAPIGiven {

    public static BusinessLogicService givenSubscribe() {

        return new BusinessLogicService() {

            @Subscribe(topic = "address 1")
            public String someMethod() {
            return "Some Method 1 return";
            }

            @Subscribe(topic = "address 2")
            public int someanotherMethod() {
            return 10;
            }

            @Subscribe(topic = "address 2")
            public Message<String> someanotherMethod() {
            return 10;
            }


        };
    }
}
