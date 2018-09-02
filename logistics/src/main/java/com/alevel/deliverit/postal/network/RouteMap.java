package com.alevel.deliverit.postal.network;

import java.util.Set;

/**
 * @author Sergey Bogovesov
 */
public class RouteMap {

    public void createDeliveryMap(PostalUnit beginUnit, PostalUnit endUnit) {
        if (!PostalNetwork.instance().containsPostalUnit(beginUnit)) {
            throw new IllegalArgumentException("Postal units doesn't contain a begin post");
        }

        if (!PostalNetwork.instance().containsPostalUnit(endUnit)) {
            throw new IllegalArgumentException("Postal units doesn't contain a end post");
        }

        if (beginUnit.getOutputs().isEmpty()) {
            throw new IllegalStateException("Begin postal unit doesn't have outputs connections");
        }

        final Set<Connection> beginUnitOutputs = beginUnit.getOutputs();
        beginUnitOutputs.forEach(connection -> {

        });
    }

    public static RouteMap instance() {
        return Singleton.INSTANCE.routeMap;
    }

    private enum Singleton {
        INSTANCE;
        private final RouteMap routeMap;

        Singleton() {
            routeMap = new RouteMap();
        }
    }
}
