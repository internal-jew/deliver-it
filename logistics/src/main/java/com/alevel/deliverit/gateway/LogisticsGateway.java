package com.alevel.deliverit.gateway;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.dijkstra.DijkstraAlgorithm;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsGateway {

    @Subscribe("logistics.calculate.distance")
    public static Route find(RouteLookupRequest request) {
        return DijkstraAlgorithm
                .builder()
                .setStartNode(request.getStartUnit())
                .setEndNode(request.getEndUnit())
                .build()
                .findShortestRoute();
    }
}
