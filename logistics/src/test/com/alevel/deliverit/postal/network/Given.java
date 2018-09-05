package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.postal.network.limitations.Limitation;
import com.alevel.deliverit.postal.network.limitations.SimpleLimitation;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Given {

    public static PostalUnit givenPostalUnit(String name) {
        return PostalUnit.builder()
                .setId(UUID.randomUUID())
                .setName("Post unit " + name)
                .build();
    }

    public static Connection givenConnection(PostalUnit start, PostalUnit end, int weight) {
        Set<Limitation> limitations = new HashSet<>();
        limitations.add(new SimpleLimitation(weight));

        return Connection.builder()
                .setStartNode(start)
                .setEndNode(end)
                .setLimitations(limitations)
                .build();
    }
}
