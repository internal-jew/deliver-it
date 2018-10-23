package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.postal.network.dijkstra.DijkstraAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sergey Bogovesov
 */
@DisplayName("PostNetwork should")
class PostNetworkTest {

    @Test
    @DisplayName("Build a map of post units and calculate distance")
    void buildNetworkMap() {
        final PostNetwork postNetwork = PostNetwork.instance();

        postNetwork.getPostOffices().forEach(System.out::println);

        assertEquals(12, DijkstraAlgorithm.builder()
                .setStartNode(postNetwork.find(8L).get())
                .setEndNode(postNetwork.find(7L).get())
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(11, DijkstraAlgorithm.builder()
                .setStartNode(postNetwork.find(1L).get())
                .setEndNode(postNetwork.find(9L).get())
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(18, DijkstraAlgorithm.builder()
                .setStartNode(postNetwork.find(9L).get())
                .setEndNode(postNetwork.find(2L).get())
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(22, DijkstraAlgorithm.builder()
                .setStartNode(postNetwork.find(6L).get())
                .setEndNode(postNetwork.find(2L).get())
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(22, DijkstraAlgorithm.builder()
                .setStartNode(postNetwork.find(3L).get())
                .setEndNode(postNetwork.find(8L).get())
                .build()
                .findShortestRoute()
                .getWeight());

        assertEquals(5, DijkstraAlgorithm.builder()
                .setStartNode(postNetwork.find(4L).get())
                .setEndNode(postNetwork.find(3L).get())
                .build()
                .findShortestRoute()
                .getWeight());
    }
}
