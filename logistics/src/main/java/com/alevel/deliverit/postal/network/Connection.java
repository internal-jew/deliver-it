package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.postal.network.limitations.Limitation;

import java.util.Set;

import static com.google.common.base.Preconditions.*;

/**
 * Implements connections between two postal office.
 *
 * @author Sergey Bogovesov
 */
public class Connection {
    private PostalUnit startNode;
    private PostalUnit endNode;
    private Set<Limitation> limitations;

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Method for calculating the weight of a connection between two postal office.
     *
     * @param sendingContext Context to be transmitted through the current connection.
     * @return Connection weight.
     */
    public int calcWeight(SendingContext sendingContext) {
        int weight = 1;
        for (Limitation limitation : limitations) {
            weight = limitation.affectWeight(weight, sendingContext);
        }
        return weight;
    }

    public PostalUnit getStartNode() {
        return startNode;
    }

    public PostalUnit getEndNode() {
        return endNode;
    }

    public Set<Limitation> getLimitations() {
        return limitations;
    }

    private Connection(PostalUnit startNode, PostalUnit endNode, Set<Limitation> limitations) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.limitations = limitations;
    }

    public static class Builder {
        private PostalUnit startNode;
        private PostalUnit endNode;
        private Set<Limitation> limitations;

        public Builder setStartNode(PostalUnit startNode) {
            this.startNode = startNode;
            return this;
        }

        public Builder setEndNode(PostalUnit endNode) {
            this.endNode = endNode;
            return this;
        }

        public Builder setLimitations(Set<Limitation> limitations) {
            this.limitations = limitations;
            return this;
        }

        public Connection build() {
            checkNotNull(startNode);
            checkNotNull(endNode);
            checkNotNull(limitations);

            if (startNode.equals(endNode)) {
                throw new IllegalArgumentException("Connections to oneself forbidden");
            }

            return new Connection(startNode, endNode, limitations);
        }
    }

    @Override
    public String toString() {
        return "Conn: {'" + startNode.getName() + "' --> '" + endNode.getName() + "'}";
    }
}
