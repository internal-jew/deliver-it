package com.alevel.deliverit.postal.network.dijkstra;

import com.alevel.deliverit.logistics.postal.network.PostOffice;

/**
 * Initial Vertex of Graph.
 * @author Sergey Bogovesov
 */
public class InitialVertex {
    private PostOffice node;
    private int weight;
    private int minWeight;

    void resetNode() {
        node = null;
        minWeight = Integer.MAX_VALUE;
    }

    void init(PostOffice node, int weight) {
        this.node = node;
        this.weight = weight;
        this.minWeight = weight;
    }

    PostOffice getNode() {
        return node;
    }

    int getWeight() {
        return weight;
    }

    int getMinWeight() {
        return minWeight;
    }

    void setNode(PostOffice node) {
        this.node = node;
    }

    boolean isExistNode() {
        return node != null;
    }
}
