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
}
