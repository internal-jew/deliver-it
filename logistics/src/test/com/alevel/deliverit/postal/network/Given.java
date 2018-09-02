package com.alevel.deliverit.postal.network;

import java.util.HashSet;
import java.util.UUID;

public class Given {

    public static PostalUnit givenPostalUnit(String name) {
        return PostalUnit.builder()
                .setId(UUID.randomUUID())
                .setName("Post unit " + name)
                .build();
    }

    public static Connection givenConnection(PostalUnit start, PostalUnit end){
        return Connection.builder()
                .setStartUnit(start)
                .setEndUnit(end)
                .setLimitations(new HashSet<>())
                .build();
    }
}
