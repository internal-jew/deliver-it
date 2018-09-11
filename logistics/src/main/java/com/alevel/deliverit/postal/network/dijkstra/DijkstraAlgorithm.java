package com.alevel.deliverit.postal.network.dijkstra;

import com.alevel.deliverit.logistics.postal.network.Connection;
import com.alevel.deliverit.logistics.postal.network.PostalUnit;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.logistics.postal.network.context.Context;
import com.alevel.deliverit.postal.network.PostalNetwork;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Calculates the weight between two post offices and also builds a route map using Dijkstra Algorithm
 *
 * @author Sergey Bogovesov
 */
public class DijkstraAlgorithm {
    private final Queue<Connection> nodesForVisit = new ArrayDeque<>();
    private final Map<PostalUnit, Integer> nodeWeights = new HashMap<>();
    private final Map<Integer, Route> routes = new HashMap<>();

    private PostalUnit startNode;
    private PostalUnit endNode;
    private InitialVertex initialVertex;
    private int currentRouteNumber;
    private Route shortestRoute;

    /**
     * @return Calculate the distance between two nodes using the Dijkstra Algorithm.
     * If @return is zero, the path was not found.
     */
    public Route findShortestRoute() {
        buildRoute(startNode.getOutputs());
        return getShortestRoute();
    }

    private void buildRoute(Set<Connection> connections) {
        final Map<PostalUnit, Integer> localWeight = new HashMap<>();

        connections.forEach(connection -> {
            final int weight = connection.calcWeight(new Context()) + initialVertex.getWeight();
            final PostalUnit currentNode = connection.getEndNode();

            if (nodeWeights.containsKey(currentNode)) {
                if (nodeWeights.get(currentNode) > weight) {
                    localWeight.put(currentNode, weight);
                }
            } else {
                localWeight.put(currentNode, weight);
            }
        });

        localWeight.forEach(nodeWeights::put);
        searchNextStartNode(localWeight);

        checkInitialNodeForVisit(connections, localWeight);

        if (isExistStartNode()) {
            fillNodeForVisit(connections, localWeight);
        } else {
            getStartNodeFromSavedForVisit();
        }
        if (isExistStartNode()) {
            buildRoute(initialVertex.getNode().getOutputs());
        }
    }

    private void checkInitialNodeForVisit(Set<Connection> connections, Map<PostalUnit, Integer> localWeight) {
        final boolean firstRoute = currentRouteNumber == 0;
        final boolean nodeForVisitIsEmpty = nodesForVisit.isEmpty();
        final boolean nodeWeightContainsEndNode = nodeWeights.containsKey(endNode);

        if (firstRoute && (nodeWeightContainsEndNode && nodeForVisitIsEmpty)) {
            connections.forEach(connection -> {
                PostalUnit node = connection.getEndNode();
                if (localWeight.containsKey(node) && !node.equals(endNode)) {
                    nodesForVisit.add(connection);
                }
            });
        }
    }

    private void getStartNodeFromSavedForVisit() {
        if (!nodesForVisit.isEmpty()) {
            final Connection connection = nodesForVisit.poll();
            checkNotNull(connection);

            PostalUnit node = connection.getEndNode();
            initialVertex.init(node, nodeWeights.get(node));

            currentRouteNumber++;
            if (!connection.getStartNode().equals(startNode)) {
                addNodeToRoute(connection.getStartNode());
            }
            addNodeToRoute(node);
        }
    }

    private void fillNodeForVisit(Set<Connection> connections, Map<PostalUnit, Integer> localWeight) {
        addNodeToRoute(initialVertex.getNode());
        connections.forEach(connection -> {
            PostalUnit node = connection.getEndNode();
            if (localWeight.containsKey(node) && !node.equals(initialVertex.getNode())) {
                nodesForVisit.add(connection);
            }
        });
    }

    private void searchNextStartNode(Map<PostalUnit, Integer> nodes) {
        initialVertex.resetNode();
        if (!nodes.containsKey(endNode)) {
            nodes.forEach((node, weight) -> {
                if (weight < initialVertex.getMinWeight()) {
                    initialVertex.init(node, weight);
                }
            });
        } else {
            addNodeToRoute(endNode);
        }
    }

    private void addNodeToRoute(PostalUnit node) {
        int weight = nodeWeights.getOrDefault(node, 0);
        if (!routes.containsKey(currentRouteNumber)) {
            Route route = new Route();
            route.addNode(startNode, weight);
            route.addNode(node, weight);
            routes.put(currentRouteNumber, route);
        } else {
            routes.get(currentRouteNumber).addNode(node, weight);
        }
    }

    private boolean isExistStartNode() {
        return initialVertex.isExistNode();
    }

    private Route getShortestRoute() {
        routes.forEach((num, route) -> {
            List<PostalUnit> nodes = route.getUnits();
            int weight = nodeWeights.getOrDefault(endNode, 0);
            if (nodes.contains(endNode) && route.getWeight() == weight) {
                shortestRoute = route;
            }
        });
        shortestRoute.print();
        return shortestRoute;
    }

    private DijkstraAlgorithm(PostalUnit startNode, PostalUnit endNode) {
        this.startNode = startNode;
        this.endNode = endNode;

        this.initialVertex = new InitialVertex();
        this.initialVertex.setNode(startNode);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PostalUnit startNode;
        private PostalUnit endNode;

        public Builder setStartNode(PostalUnit startNode) {
            this.startNode = startNode;
            return this;
        }

        public Builder setEndNode(PostalUnit endNode) {
            this.endNode = endNode;
            return this;
        }

        public DijkstraAlgorithm build() {
            checkNotNull(startNode);
            checkNotNull(endNode);

            final PostalNetwork instance = PostalNetwork.instance();

            if (!instance.containsPostalUnit(startNode)) {
                throw new IllegalArgumentException("Postal units doesn't contain a begin node");
            }

            if (!instance.containsPostalUnit(endNode)) {
                throw new IllegalArgumentException("Postal units doesn't contain a end node");
            }

            if (startNode.getOutputs().isEmpty()) {
                throw new IllegalStateException("Start node doesn't have outputs connections");
            }

            if (endNode.getInputs().isEmpty()) {
                throw new IllegalStateException("End node doesn't have inputs connections");
            }

            return new DijkstraAlgorithm(startNode, endNode);
        }
    }
}
