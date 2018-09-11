package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.logistics.postal.network.Connection;
import com.alevel.deliverit.logistics.postal.network.PostalUnit;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a network of postal offices and connections between them.
 *
 * @author Sergey Bogovesov
 */
public class PostalNetwork {

    private Set<PostalUnit> postalUnits = new HashSet<>();
    private Set<Connection> connections = new HashSet<>();

    private void addPostalUnit(PostalUnit postalUnit) {
        postalUnits.add(postalUnit);
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
        addConnectionToPostalUnit(connection);
    }

    private void addConnectionToPostalUnit(Connection connection) {
        addPostalUnit(connection.getStartNode());
        addPostalUnit(connection.getEndNode());

        postalUnits.forEach(postalUnit -> {
            if (postalUnit.equals(connection.getStartNode())) {
                postalUnit.addOutputConnection(connection);
            } else if (postalUnit.equals(connection.getEndNode())) {
                postalUnit.addInputConnection(connection);
            }
        });
    }

    public boolean containsPostalUnit(PostalUnit postalUnit) {
        return postalUnits.contains(postalUnit);
    }

    public Set<PostalUnit> getPostalUnits() {
        return postalUnits;
    }

    public Set<Connection> getConnections() {
        return connections;
    }

    public static PostalNetwork instance() {
        return Singleton.INSTANCE.postalNetwork;
    }

    private enum Singleton {
        INSTANCE;
        private final PostalNetwork postalNetwork;

        Singleton() {
            postalNetwork = new PostalNetwork();
        }
    }
}
