package com.alevel.deliverit.logistics.postal.network;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a network of postal offices and connections between them.
 *
 * @author Sergey Bogovesov
 */
public class PostNetwork {

    static {
        PostalNetworkCreator.buildFakeNetwork();
    }

    private Set<PostOffice> postOffices = new HashSet<>();
    private Set<Connection> connections = new HashSet<>();

    private void addPostOffice(PostOffice postOffice) {
        postOffices.add(postOffice);
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
        addConnectionToNetwork(connection);
    }

    private void addConnectionToNetwork(Connection connection) {
        addPostOffice(connection.getStartNode());
        addPostOffice(connection.getEndNode());

        postOffices.forEach(postalUnit -> {
            if (postalUnit.equals(connection.getStartNode())) {
                postalUnit.addOutputConnection(connection);
            } else if (postalUnit.equals(connection.getEndNode())) {
                postalUnit.addInputConnection(connection);
            }
        });
    }

    public Optional<PostOffice> find(Long id) {
        return postOffices.stream()
                .filter(office -> office.getId().getValue().equals(id))
                .findFirst();
    }

    public Optional<PostOffice> find(String postCode) {
        return postOffices.stream()
                .filter(office -> office.getPostCode().equals(postCode))
                .findFirst();
    }

    public boolean contains(PostOffice postOffice) {
        return postOffices.contains(postOffice);
    }

    public Set<PostOffice> getPostOffices() {
        return postOffices;
    }

    public Set<Connection> getConnections() {
        return connections;
    }

    public static PostNetwork instance() {
        return Singleton.INSTANCE.postNetwork;
    }

    private enum Singleton {
        INSTANCE;
        private final PostNetwork postNetwork;

        Singleton() {
            postNetwork = new PostNetwork();
        }
    }
}
