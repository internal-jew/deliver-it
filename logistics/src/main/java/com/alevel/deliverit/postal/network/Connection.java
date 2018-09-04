package com.alevel.deliverit.postal.network;

import java.util.Set;

import static com.google.common.base.Preconditions.*;

/**
 * Implements connections between two postal unit
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
     * Method for calculating the weight of a connection between two postal unit
     *
     * @param sendingContext context to be transmitted through the current connection
     * @return connection weight
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
        private PostalUnit startUnit;
        private PostalUnit endUnit;
        private Set<Limitation> limitations;

        public Builder setStartUnit(PostalUnit startUnit) {
            this.startUnit = startUnit;
            return this;
        }

        public Builder setEndUnit(PostalUnit endUnit) {
            this.endUnit = endUnit;
            return this;
        }

        public Builder setLimitations(Set<Limitation> limitations) {
            this.limitations = limitations;
            return this;
        }

        public Connection build() {
            checkNotNull(startUnit);
            checkNotNull(endUnit);
            checkNotNull(limitations);

            if (startUnit.equals(endUnit)) {
                throw new IllegalArgumentException("Connections to oneself forbidden");
            }

            return new Connection(startUnit, endUnit, limitations);
        }
    }

    @Override
    public String toString() {
        return "Conn: {'" + startNode.getName() + "' --> '" + endNode.getName() + "'}";
    }
}
