package com.alevel.deliverit.postal.network;

import java.util.HashSet;
import java.util.Set;

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
