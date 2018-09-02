package com.alevel.deliverit.postal.network;

import java.util.*;

/**
 * @author Sergey Bogovesov
 */
public class PostalNetwork {

    private Set<PostalUnit> postalUnits = new HashSet<>();
    private Set<Connection> connections = new HashSet<>();

    public void addPostalUnit(PostalUnit postalUnit) {
        postalUnits.add(postalUnit);
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
        addConnectionToPostalUnit(connection);
    }

    private void addConnectionToPostalUnit(Connection connection) {
        addPostalUnit(connection.getStartUnit());
        addPostalUnit(connection.getEndUnit());

        postalUnits.forEach(postalUnit -> {
            if (connection.getStartUnit().equals(postalUnit)) {
                postalUnit.addOutputConnection(connection);
            } else if (connection.getEndUnit().equals(postalUnit)) {
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
