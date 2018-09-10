package com.alevel.deliverit.postal.network.dijkstra;

import com.alevel.deliverit.postal.network.PostalUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the dijkstra selected at the current stage of construction.
 *
 * @author Sergey Bogovesov
 */
public class Route {
    public static final int START_NODE = 0;

    private final List<PostalUnit> nodes = new ArrayList<>();
    private int weight;

    public void addNode(PostalUnit node, int weight) {
        nodes.add(node);
        this.weight = weight;
    }

    public List<PostalUnit> getUnits() {
        return nodes;
    }

    public int getWeight() {
        return weight;
    }

    public void print() {
        PostalUnit startNode = nodes.get(START_NODE);
        PostalUnit endNode = nodes.get(nodes.size() - 1);

        System.out.println("Route from '" + startNode.getName() + "' to '" + endNode.getName() + "'");
        System.out.print("[");
        nodes.forEach(node -> System.out.print(node.getName() + " --> "));
        System.out.print("weight: " + getWeight() + "]");
        System.out.println();
    }
}
