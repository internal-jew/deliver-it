package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.postal.network.constraint.Constraint;
import com.alevel.deliverit.postal.network.context.SendingContext;

import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Preconditions.*;

/**
 * Implements connection between two postal offices.
 *
 * @author Sergey Bogovesov
 */
public class Connection {
    private PostalUnit startNode;
    private PostalUnit endNode;
    private Set<Constraint> constraints;

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Calculates the weight of a connection between two postal office.
     *
     * @param {@link SendingContext context to be transmitted through the current connection.}
     * @return Connection weight.
     */
    public int calcWeight(SendingContext sendingContext) {
        int weight = 1;
        for (Constraint constraint : constraints) {
            weight = constraint.affectWeight(weight, sendingContext);
        }
        return weight;
    }

    public PostalUnit getStartNode() {
        return startNode;
    }

    public PostalUnit getEndNode() {
        return endNode;
    }

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    private Connection(PostalUnit startNode, PostalUnit endNode, Set<Constraint> constraints) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.constraints = constraints;
    }

    public static class Builder {
        private PostalUnit startNode;
        private PostalUnit endNode;
        private Set<Constraint> constraints;

        public Builder setStartNode(PostalUnit startNode) {
            this.startNode = startNode;
            return this;
        }

        public Builder setEndNode(PostalUnit endNode) {
            this.endNode = endNode;
            return this;
        }

        public Builder setConstraints(Set<Constraint> constraints) {
            this.constraints = constraints;
            return this;
        }

        public Connection build() {
            checkNotNull(startNode);
            checkNotNull(endNode);
            checkNotNull(constraints);

            if (startNode.equals(endNode)) {
                throw new IllegalArgumentException("Connections to oneself forbidden");
            }

            return new Connection(startNode, endNode, constraints);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection)) return false;
        Connection that = (Connection) o;
        return Objects.equals(getStartNode(), that.getStartNode()) &&
                Objects.equals(getEndNode(), that.getEndNode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getStartNode(), getEndNode());
    }

    @Override
    public String toString() {
        return "Conn: {'" + startNode.getName() + "' --> '" + endNode.getName() + "'}";
    }
}
