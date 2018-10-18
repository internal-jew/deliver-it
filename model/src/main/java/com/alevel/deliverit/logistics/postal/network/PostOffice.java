package com.alevel.deliverit.logistics.postal.network;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.entity.Entity;
import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A post office.
 *
 * @author Sergey Bogovesov
 */
public class PostOffice extends Entity<PostOfficeId> {
    private String postCode;
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

    private PostOffice(PostOfficeId id, String postCode) {
        super(id);
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public ImmutableSet<Connection> getInputs() {
        return ImmutableSet.copyOf(inputs);
    }

    public ImmutableSet<Connection> getOutputs() {
        return ImmutableSet.copyOf(outputs);
    }

    public static class Builder {
        private PostOfficeId id;
        private String postCode;

        public Builder setId(PostOfficeId id) {
            this.id = id;
            return this;
        }

        public Builder setPostCode(String name) {
            this.postCode = name;
            return this;
        }

        public PostOffice build() {
            checkNotNull(id);
            checkNotNull(postCode);

            return new PostOffice(id, postCode);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostOffice)) return false;
        PostOffice that = (PostOffice) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getPostCode(), that.getPostCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getPostCode());
    }

    @Override
    public String toString() {

        return "PostOffice{" +
                /*"id='" + id + '\'' +*/
                "  postCode='" + postCode + '\'' +
                ", inputs=" + getConnectionsAsString(inputs) +
                ", outputs=" + getConnectionsAsString(outputs) +
                '}';
    }

    public static Parser<PostOffice> parser() {
        return new PostOfficeParser();
    }
}
