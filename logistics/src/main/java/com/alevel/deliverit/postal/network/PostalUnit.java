package com.alevel.deliverit.postal.network;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.google.common.base.Preconditions.*;

/**
 * Represents a post office where a parcel can be delivered.
 *
 * @author Sergey Bogovesov
 */
public class PostalUnit {
    private UUID id;
    private String name;
    private Set<Connection> inputs = new HashSet<>();
    private Set<Connection> outputs = new HashSet<>();

    public static Builder builder() {
        return new Builder();
    }

    public void addInputConnection(Connection connection) {
        inputs.add(connection);
    }

    public void addOutputConnection(Connection connection) {
        outputs.add(connection);
    }

    private PostalUnit(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Connection> getInputs() {
        return inputs;
    }

    public Set<Connection> getOutputs() {
        return outputs;
    }

    public static class Builder {
        private UUID id;
        private String name;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public PostalUnit build() {
            checkNotNull(id);
            checkNotNull(name);

            return new PostalUnit(id, name);
        }
    }

    private String getConnectionsAsString(Set<Connection> connections) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Connection connection : connections) {
            stringBuilder.append(connection).append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        return "PostalUnit{" +
                /*"id='" + id + '\'' +*/
                "  name='" + name + '\'' +
                ", inputs=" + getConnectionsAsString(inputs) +
                ", outputs=" + getConnectionsAsString(outputs) +
                '}';
    }
}
