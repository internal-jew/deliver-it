package com.alevel.deliverit.logistics.fsm;

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Map;

import static com.alevel.deliverit.logistics.fsm.State.*;

public class RouteMap {

//    ***Possible states***
//
//    STANDBY -> ACCEPTING -> WEIGHING -> RADIATION_CONTROL -> STAMPING -> DEPARTED
//                  \        /                                                  \
//                   \     /                                                     \
//                    TRASH                                                      LOST
//

    public static Map<State, ImmutableSet<State>> routeMap = new HashMap<>();

    private static ImmutableSet<State> transitionsTerminal = ImmutableSet.of(ACCEPTING);
    private static ImmutableSet<State> transitionsAccepting = ImmutableSet.of(WEIGHTING, TRASH);
    private static ImmutableSet<State> transitionsWeighting = ImmutableSet.of(RADIATION_CONTROL, TRASH);
    private static ImmutableSet<State> transitionsRadiationControl = ImmutableSet.of(STAMPING);
    private static ImmutableSet<State> transitionsStamping = ImmutableSet.of(DEPARTED);
    private static ImmutableSet<State> transitionsDeparted = ImmutableSet.of(STANDBY, LOST);
//    private static ImmutableSet<State> transitionsTrash = ImmutableSet.of();
//    private static ImmutableSet<State> transitionsLost = ImmutableSet.of();

    static {
        routeMap.put(STANDBY, transitionsTerminal);
        routeMap.put(ACCEPTING, transitionsAccepting);
        routeMap.put(WEIGHTING, transitionsWeighting);
        routeMap.put(RADIATION_CONTROL, transitionsRadiationControl);
        routeMap.put(STAMPING, transitionsStamping);
        routeMap.put(DEPARTED, transitionsDeparted);
//        routeMap.put(TRASH, transitionsTrash);
//        routeMap.put(LOST, transitionsLost);
    }
}
