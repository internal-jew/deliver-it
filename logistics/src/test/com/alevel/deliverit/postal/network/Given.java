package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.logistics.postal.network.Connection;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.PostOfficeId;
import com.alevel.deliverit.logistics.postal.network.constraint.Constraint;
import com.alevel.deliverit.logistics.postal.network.constraint.SimpleConstraint;

import java.util.HashSet;
import java.util.Set;

public class Given {

    public static PostOffice givenPostOffice(Long id) {
        return PostOffice.builder()
                .setId(new PostOfficeId(id))
                .setName("Post unit " + id)
                .build();
    }

    public static Connection givenConnection(PostOffice start, PostOffice end, int weight) {
        Set<Constraint> constraints = new HashSet<>();
        constraints.add(new SimpleConstraint(weight));

        return Connection.builder()
                .setStartNode(start)
                .setEndNode(end)
                .setConstraints(constraints)
                .build();
    }
}
