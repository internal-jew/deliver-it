package com.alevel.deliverit.postal.network;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author Sergey Bogovesov
 */
public class RouteMap {
    private final Queue<Connection> nodesForVisit = new ArrayDeque<>();
    private final Map<PostalUnit, Integer> nodeWeights = new HashMap<>();

    private PostalUnit finishNode;
    private PostalUnit startNode;
    private int weightStartNode;
    private int distance;

    public int createDeliveryMap(PostalUnit startNode, PostalUnit endNode) {
        if (!PostalNetwork.instance().containsPostalUnit(startNode)) {
            throw new IllegalArgumentException("Postal units doesn't contain a begin post");
        }

        if (!PostalNetwork.instance().containsPostalUnit(endNode)) {
            throw new IllegalArgumentException("Postal units doesn't contain a end post");
        }

        if (startNode.getOutputs().isEmpty()) {
            throw new IllegalStateException("Begin node doesn't have outputs connections");
        }

        if (endNode.getInputs().isEmpty()) {
            throw new IllegalStateException("End node doesn't have inputs connections");
        }

        this.finishNode = endNode;
        this.startNode = startNode;

        weightStartNode = 0;
        distance = 0;
        nodesForVisit.clear();
        nodeWeights.clear();

        buildRoute(startNode.getOutputs());
        return distance;
    }

    private void buildRoute(Set<Connection> connections) {
        final Map<PostalUnit, Integer> localWeight = new HashMap<>();
        connections.forEach(connection -> {
            final int weight = connection.calcWeight(new SendingContext()) + weightStartNode;
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

        startNode = null;
        if (!localWeight.containsKey(finishNode)) {
            // поиск следующей вершины для посещения по минимальному расстоянию
            localWeight.forEach((node, weight) -> {
                int minWeight = Integer.MAX_VALUE;
                if (weight < minWeight) {
                    startNode = node;
                }
            });
        }

        if (startNode != null) {
            // добавление в список вершин для посещения
            connections.forEach(connection -> {
                PostalUnit node = connection.getEndNode();
                if ((localWeight.containsKey(node)) && (!node.equals(startNode))) {
                    nodesForVisit.add(connection);
                }
            });
        } else {
            // если начальной вершины нету то берем её из списка доступных к посещению
            if (!nodesForVisit.isEmpty()) {
                final Connection connection = nodesForVisit.poll();
                startNode = connection.getEndNode();
            }
        }

        if (startNode != null) {
            weightStartNode = nodeWeights.get(startNode);
            buildRoute(startNode.getOutputs());
        } else if (nodeWeights.containsKey(finishNode)) {
            distance = nodeWeights.get(finishNode);
            System.out.println("Distance to node " + finishNode.getName() + " is " + nodeWeights.get(finishNode));
        }
    }

    public static RouteMap instance() {
        return Singleton.INSTANCE.routeMap;
    }

    private enum Singleton {
        INSTANCE;
        private final RouteMap routeMap;

        Singleton() {
            routeMap = new RouteMap();
        }
    }
}
