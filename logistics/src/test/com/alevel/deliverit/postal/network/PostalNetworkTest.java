package com.alevel.deliverit.postal.network;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

import static com.alevel.deliverit.postal.network.Given.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sergey Bogovesov
 */
@DisplayName("PostalNetwork should")
class PostalNetworkTest {

    @Test
    @DisplayName("Build a map of post units")
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

        postalNetwork.addConnection(givenConnection(postalUnit1, postalUnit2));
        postalNetwork.addConnection(givenConnection(postalUnit1, postalUnit3));

        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit3));
        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit4));
        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit6));
        postalNetwork.addConnection(givenConnection(postalUnit2, postalUnit8));

        postalNetwork.addConnection(givenConnection(postalUnit3, postalUnit5));
        postalNetwork.addConnection(givenConnection(postalUnit3, postalUnit7));

        postalNetwork.addConnection(givenConnection(postalUnit4, postalUnit1));
        postalNetwork.addConnection(givenConnection(postalUnit4, postalUnit3));
        postalNetwork.addConnection(givenConnection(postalUnit4, postalUnit5));

        postalNetwork.addConnection(givenConnection(postalUnit5, postalUnit6));
        postalNetwork.addConnection(givenConnection(postalUnit5, postalUnit7));

        postalNetwork.addConnection(givenConnection(postalUnit6, postalUnit3));
        postalNetwork.addConnection(givenConnection(postalUnit6, postalUnit10));

        postalNetwork.addConnection(givenConnection(postalUnit7, postalUnit1));
        postalNetwork.addConnection(givenConnection(postalUnit7, postalUnit10));

        postalNetwork.addConnection(givenConnection(postalUnit8, postalUnit5));
        postalNetwork.addConnection(givenConnection(postalUnit8, postalUnit9));

        postalNetwork.addConnection(givenConnection(postalUnit9, postalUnit6));
        postalNetwork.addConnection(givenConnection(postalUnit9, postalUnit10));

        postalNetwork.addConnection(givenConnection(postalUnit10, postalUnit4));
        postalNetwork.addConnection(givenConnection(postalUnit10, postalUnit5));
        postalNetwork.addConnection(givenConnection(postalUnit10, postalUnit9));

        postalNetwork.getPostalUnits().forEach(System.out::println);
    }
}
