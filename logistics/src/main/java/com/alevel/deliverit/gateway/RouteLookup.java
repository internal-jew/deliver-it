package com.alevel.deliverit.gateway;

import com.alevel.deliverit.BusinessLogicService;
import com.alevel.deliverit.Subscribe;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.PostNetwork;
import com.alevel.deliverit.postal.network.dijkstra.DijkstraAlgorithm;

import java.util.Optional;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookup implements BusinessLogicService {

    @Subscribe("logistics.calculate.distance")
    public static Route find(RouteLookupRequest request) {
        PostOffice startOffice = getPostOffice(request.getStart());
        PostOffice endOffice = getPostOffice(request.getFinish());

        return DijkstraAlgorithm
                .builder()
                .setStartNode(startOffice)
                .setEndNode(endOffice)
                .build()
                .findShortestRoute();
    }

    private static PostOffice getPostOffice(Long id) {
        final Optional<PostOffice> postOffice = PostNetwork.instance().find(id);
        if (postOffice.isPresent()) {
            return postOffice.get();
        } else {
            throw new IllegalArgumentException("Post office with that id not found!");
        }
    }
}
