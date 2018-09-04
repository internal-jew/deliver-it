package com.alevel.deliverit.postal.network;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

import static com.alevel.deliverit.postal.network.Given.*;
import static com.alevel.deliverit.postal.network.RouteMap.instance;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sergey Bogovesov
 */
@DisplayName("PostalNetwork should")
class PostalNetworkTest {

    @Test
    @DisplayName("Build a map of post units and calc distance")
    void buildNetworkMap() {
        PostalUnit postalUnit1 = givenPostalUnit("1");
        PostalUnit postalUnit2 = givenPostalUnit("2");
        PostalUnit postalUnit3 = givenPostalUnit("3");
        PostalUnit postalUnit4 = givenPostalUnit("4");
        PostalUnit postalUnit5 = givenPostalUnit("5");
        PostalUnit postalUnit6 = givenPostalUnit("6");
        PostalUnit postalUnit7 = givenPostalUnit("7");
        PostalUnit postalUnit8 = givenPostalUnit("8");
        PostalUnit postalUnit9 = givenPostalUnit("9");
        PostalUnit postalUnit10 = givenPostalUnit("10");

        final PostalNetwork postalNetwork = PostalNetwork.instance();

        postalNetwork.addPostalUnit(postalUnit1);
        postalNetwork.addPostalUnit(postalUnit2);
        postalNetwork.addPostalUnit(postalUnit3);
        postalNetwork.addPostalUnit(postalUnit3);

        postalNetwork.addConnection(givenConnection(postalUnit1, postalUnit2, 5));
        postalNetwork.addConnection(givenConnection(postalUnit1, postalUnit3, 2));

        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit3, 4));
        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit4, 2));
        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit6, 1));
        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit8, 3));

        postalNetwork.addConnection(givenConnection(postalUnit3, postalUnit5, 7));
        postalNetwork.addConnection(givenConnection(postalUnit3, postalUnit7, 8));

        postalNetwork.addConnection(givenConnection(postalUnit4, postalUnit1, 3));
        postalNetwork.addConnection(givenConnection(postalUnit4, postalUnit3, 10));
        postalNetwork.addConnection(givenConnection(postalUnit4, postalUnit5, 1));

        postalNetwork.addConnection(givenConnection(postalUnit5, postalUnit6, 1));
        postalNetwork.addConnection(givenConnection(postalUnit5, postalUnit7, 8));

        postalNetwork.addConnection(givenConnection(postalUnit6, postalUnit3, 5));
        postalNetwork.addConnection(givenConnection(postalUnit6, postalUnit10, 5));

        postalNetwork.addConnection(givenConnection(postalUnit7, postalUnit1, 6));
        postalNetwork.addConnection(givenConnection(postalUnit7, postalUnit10, 1));

        postalNetwork.addConnection(givenConnection(postalUnit8, postalUnit5, 4));
        postalNetwork.addConnection(givenConnection(postalUnit8, postalUnit9, 3));

        postalNetwork.addConnection(givenConnection(postalUnit9, postalUnit6, 5));
        postalNetwork.addConnection(givenConnection(postalUnit9, postalUnit10, 1));

        postalNetwork.addConnection(givenConnection(postalUnit10, postalUnit4, 9));
        postalNetwork.addConnection(givenConnection(postalUnit10, postalUnit5, 6));
        postalNetwork.addConnection(givenConnection(postalUnit10, postalUnit9, 2));

        postalNetwork.getPostalUnits().forEach(System.out::println);

        assertEquals(12, instance().createDeliveryMap(postalUnit8, postalUnit7));
        assertEquals(10, instance().createDeliveryMap(postalUnit8, postalUnit3));
        assertEquals(11, instance().createDeliveryMap(postalUnit1, postalUnit9));
        assertEquals(13, instance().createDeliveryMap(postalUnit9, postalUnit1));
    }
}
