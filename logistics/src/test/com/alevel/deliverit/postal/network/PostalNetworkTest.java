package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.logistics.postal.network.PostalUnit;
import com.alevel.deliverit.postal.network.dijkstra.DijkstraAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.alevel.deliverit.postal.network.Given.givenConnection;
import static com.alevel.deliverit.postal.network.Given.givenPostalUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sergey Bogovesov
 */
@DisplayName("PostalNetwork should")
class PostalNetworkTest {

    @Test
    @DisplayName("Build a map of post units and calculate distance")
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

        assertEquals(12, DijkstraAlgorithm.builder()
                .setStartNode(postalUnit8)
                .setEndNode(postalUnit7)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(11, DijkstraAlgorithm.builder()
                .setStartNode(postalUnit1)
                .setEndNode(postalUnit9)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(18, DijkstraAlgorithm.builder()
                .setStartNode(postalUnit9)
                .setEndNode(postalUnit2)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(22, DijkstraAlgorithm.builder()
                .setStartNode(postalUnit6)
                .setEndNode(postalUnit2)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(22, DijkstraAlgorithm.builder()
                .setStartNode(postalUnit3)
                .setEndNode(postalUnit8)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(5, DijkstraAlgorithm.builder()
                .setStartNode(postalUnit4)
                .setEndNode(postalUnit3)
                .build()
                .findShortestRoute()
                .getWeight());
    }
}
