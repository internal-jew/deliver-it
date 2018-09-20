package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.postal.network.dijkstra.DijkstraAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.alevel.deliverit.postal.network.Given.givenConnection;
import static com.alevel.deliverit.postal.network.Given.givenPostalUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sergey Bogovesov
 */
@DisplayName("PostNetwork should")
class PostNetworkTest {

    @Test
    @DisplayName("Build a map of post units and calculate distance")
    void buildNetworkMap() {
        PostOffice postOffice1 = givenPostalUnit(1l);
        PostOffice postOffice2 = givenPostalUnit(2l);
        PostOffice postOffice3 = givenPostalUnit(3l);
        PostOffice postOffice4 = givenPostalUnit(4l);
        PostOffice postOffice5 = givenPostalUnit(5l);
        PostOffice postOffice6 = givenPostalUnit(6l);
        PostOffice postOffice7 = givenPostalUnit(7l);
        PostOffice postOffice8 = givenPostalUnit(8l);
        PostOffice postOffice9 = givenPostalUnit(9l);
        PostOffice postOffice10 = givenPostalUnit(10l);

        final PostNetwork postNetwork = PostNetwork.instance();

        postNetwork.addConnection(givenConnection(postOffice1, postOffice2, 5));
        postNetwork.addConnection(givenConnection(postOffice1, postOffice3, 2));

        postNetwork.addConnection(givenConnection(postOffice2, postOffice3, 4));
        postNetwork.addConnection(givenConnection(postOffice2, postOffice4, 2));
        postNetwork.addConnection(givenConnection(postOffice2, postOffice6, 1));
        postNetwork.addConnection(givenConnection(postOffice2, postOffice8, 3));

        postNetwork.addConnection(givenConnection(postOffice3, postOffice5, 7));
        postNetwork.addConnection(givenConnection(postOffice3, postOffice7, 8));

        postNetwork.addConnection(givenConnection(postOffice4, postOffice1, 3));
        postNetwork.addConnection(givenConnection(postOffice4, postOffice3, 10));
        postNetwork.addConnection(givenConnection(postOffice4, postOffice5, 1));

        postNetwork.addConnection(givenConnection(postOffice5, postOffice6, 1));
        postNetwork.addConnection(givenConnection(postOffice5, postOffice7, 8));

        postNetwork.addConnection(givenConnection(postOffice6, postOffice3, 5));
        postNetwork.addConnection(givenConnection(postOffice6, postOffice10, 5));

        postNetwork.addConnection(givenConnection(postOffice7, postOffice1, 6));
        postNetwork.addConnection(givenConnection(postOffice7, postOffice10, 1));

        postNetwork.addConnection(givenConnection(postOffice8, postOffice5, 4));
        postNetwork.addConnection(givenConnection(postOffice8, postOffice9, 3));

        postNetwork.addConnection(givenConnection(postOffice9, postOffice6, 5));
        postNetwork.addConnection(givenConnection(postOffice9, postOffice10, 1));

        postNetwork.addConnection(givenConnection(postOffice10, postOffice4, 9));
        postNetwork.addConnection(givenConnection(postOffice10, postOffice5, 6));
        postNetwork.addConnection(givenConnection(postOffice10, postOffice9, 2));

        postNetwork.getPostOffices().forEach(System.out::println);

        assertEquals(12, DijkstraAlgorithm.builder()
                .setStartNode(postOffice8)
                .setEndNode(postOffice7)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(11, DijkstraAlgorithm.builder()
                .setStartNode(postOffice1)
                .setEndNode(postOffice9)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(18, DijkstraAlgorithm.builder()
                .setStartNode(postOffice9)
                .setEndNode(postOffice2)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(22, DijkstraAlgorithm.builder()
                .setStartNode(postOffice6)
                .setEndNode(postOffice2)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(22, DijkstraAlgorithm.builder()
                .setStartNode(postOffice3)
                .setEndNode(postOffice8)
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(5, DijkstraAlgorithm.builder()
                .setStartNode(postOffice4)
                .setEndNode(postOffice3)
                .build()
                .findShortestRoute()
                .getWeight());
    }
}
