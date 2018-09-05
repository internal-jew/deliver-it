package com.alevel.deliverit.postal.network.route;

import com.alevel.deliverit.postal.network.Connection;
import com.alevel.deliverit.postal.network.PostalNetwork;
import com.alevel.deliverit.postal.network.PostalUnit;
import com.alevel.deliverit.postal.network.SendingContext;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class calculates the weight between two post offices and also builds a route map.
 *
 * @author Sergey Bogovesov
 */
public class RouteMap {
    private final Queue<Connection> nodesForVisit = new ArrayDeque<>();
    private final Map<PostalUnit, Integer> nodeWeights = new HashMap<>();
    private final Map<Integer, Route> routes = new HashMap<>();

    private PostalUnit startNode;
    private PostalUnit endNode;
    private InitialVertex initialVertex;
    private int currentNumRoute;

    /**
     * @return Calculate the distance between two nodes using the Dijkstra Algorithm.
     * If @return is zero, the path was not found.
     */
    public int calcDistance() {
        buildRoute(startNode.getOutputs());
        return getCalculatedDistance();
    }

    private void buildRoute(Set<Connection> connections) {
        final Map<PostalUnit, Integer> localWeight = new HashMap<>();

        connections.forEach(connection -> {
            final int weight = connection.calcWeight(new SendingContext()) + initialVertex.getWeight();
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

        if (isExistStartNode()) {
            fillNodeForVisit(connections, localWeight);
        } else {
            getStartNodeFromSavedForVisit();
        }
        if (isExistStartNode()) {
            buildRoute(initialVertex.getNode().getOutputs());
        }
    }

    private void getStartNodeFromSavedForVisit() {
        if (!nodesForVisit.isEmpty()) {
            final Connection connection = nodesForVisit.poll();
            assert null != connection;

            PostalUnit node = connection.getEndNode();
            initialVertex.init(node, nodeWeights.get(node));

            currentNumRoute++;
            if (!connection.getStartNode().equals(this.startNode)) {
                addNodeToRoute(connection.getStartNode(), nodeWeights.get(node));
            }
            addNodeToRoute(node, nodeWeights.get(node));
        }
    }

    private void fillNodeForVisit(Set<Connection> connections, Map<PostalUnit, Integer> localWeight) {
        addNodeToRoute(initialVertex.getNode(), nodeWeights.get(initialVertex.getNode()));
        connections.forEach(connection -> {
            PostalUnit node = connection.getEndNode();

            if ((localWeight.containsKey(node)) && (!node.equals(initialVertex.getNode()))) {
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
            addNodeToRoute(endNode, nodeWeights.get(endNode));
        }
    }

    private void addNodeToRoute(PostalUnit node, int weight) {
        if (!routes.containsKey(currentNumRoute)) {
            Route route = new Route();
            route.addNode(node, weight);
            routes.put(currentNumRoute, route);
        } else {
            routes.get(currentNumRoute).addNode(node, weight);
        }
    }

    private boolean isExistStartNode() {
        return initialVertex.isExistNode();
    }

    private int getCalculatedDistance() {
        printRoutes();
        return nodeWeights.getOrDefault(endNode, 0);
    }

    private void printRoutes() {
        routes.forEach((num, route) -> {
            List<PostalUnit> nodes = route.getNodes();
            int weight = nodeWeights.getOrDefault(endNode, 0);

            if ((nodes.contains(endNode)) && (route.getWeight() == weight)) {
                System.out.println("Route from '" + startNode.getName() + "' to '" + endNode.getName() + "'");

                System.out.print("[" + startNode.getName());
                nodes.forEach(node -> System.out.print(" -> " + node.getName()));
                System.out.print("] weight: " + route.getWeight());
                System.out.println();
            }
        });
    }

    private RouteMap(PostalUnit startNode, PostalUnit endNode) {
        this.startNode = startNode;
        this.endNode = endNode;

        this.initialVertex = new InitialVertex();
        this.initialVertex.setNode(startNode);

        addNodeToRoute(startNode, 0);
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

        public RouteMap build() {
            checkNotNull(startNode);
            checkNotNull(endNode);

            if (!PostalNetwork.instance().containsPostalUnit(startNode)) {
                throw new IllegalArgumentException("Postal units doesn't contain a begin node");
            }

            if (!PostalNetwork.instance().containsPostalUnit(endNode)) {
                throw new IllegalArgumentException("Postal units doesn't contain a end node");
            }

            if (startNode.getOutputs().isEmpty()) {
                throw new IllegalStateException("Begin node doesn't have outputs connections");
            }

            if (endNode.getInputs().isEmpty()) {
                throw new IllegalStateException("End node doesn't have inputs connections");
            }

            return new RouteMap(startNode, endNode);
        }
    }
}
