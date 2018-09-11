package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.logistics.postal.network.Connection;
import com.alevel.deliverit.logistics.postal.network.PostalUnit;
import com.alevel.deliverit.logistics.postal.network.constraint.Constraint;
import com.alevel.deliverit.logistics.postal.network.constraint.SimpleConstraint;

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
        Set<Constraint> constraints = new HashSet<>();
        constraints.add(new SimpleConstraint(weight));

        return Connection.builder()
                .setStartNode(start)
                .setEndNode(end)
                .setConstraints(constraints)
                .build();
    }
}
