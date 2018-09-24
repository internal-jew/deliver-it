package com.alevel.deliverit.logistics.postal.network;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the dijkstra selected at the current stage of construction.
 *
 * @author Sergey Bogovesov
 */
public class Route {
    public static final int START_NODE = 0;

    private final List<PostOffice> nodes = new ArrayList<>();
    private int weight;

    public void addNode(PostOffice node, int weight) {
        nodes.add(node);
        this.weight = weight;
    }

    public List<PostOffice> getUnits() {
        return nodes;
    }

    public int getWeight() {
        return weight;
    }

    public void print() {
        PostOffice startNode = nodes.get(START_NODE);
        PostOffice endNode = nodes.get(nodes.size() - 1);

        System.out.println("Route from '" + startNode.getPostalCode() + "' to '" + endNode.getPostalCode() + "'");
        System.out.print("[");
        nodes.forEach(node -> System.out.print(node.getPostalCode() + " --> "));
        System.out.print("weight: " + getWeight() + "]");
        System.out.println();
    }
}
