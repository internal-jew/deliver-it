package com.alevel.deliverit.postal.network.dijkstra;

import com.alevel.deliverit.logistics.postal.network.PostalUnit;

/**
 * Initial Vertex of Graph.
 * @author Sergey Bogovesov
 */
public class InitialVertex {
    private PostalUnit node;
    private int weight;
    private int minWeight;

    void resetNode() {
        node = null;
        minWeight = Integer.MAX_VALUE;
    }

    void init(PostalUnit node, int weight) {
        this.node = node;
        this.weight = weight;
        this.minWeight = weight;
    }

    PostalUnit getNode() {
        return node;
    }

    int getWeight() {
        return weight;
    }

    int getMinWeight() {
        return minWeight;
    }

    void setNode(PostalUnit node) {
        this.node = node;
    }

    boolean isExistNode() {
        return node != null;
    }
}
