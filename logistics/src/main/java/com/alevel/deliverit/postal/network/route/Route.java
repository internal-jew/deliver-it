package com.alevel.deliverit.postal.network.route;

import com.alevel.deliverit.postal.network.PostalUnit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Represents the route selected at the current stage of construction.
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

    public List<PostalUnit> getNodes() {
        return nodes;
    }

    public int getWeight() {
        return weight;
    }
}
