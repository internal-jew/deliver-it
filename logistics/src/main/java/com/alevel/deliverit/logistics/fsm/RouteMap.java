package com.alevel.deliverit.logistics.fsm;

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Map;

import static com.alevel.deliverit.logistics.fsm.State.*;

public class RouteMap {

    public static Map<State, ImmutableSet<State>> routeMap = new HashMap<>();

    private static ImmutableSet<State> transitionsStart = ImmutableSet.of(PROCESS);
    private static ImmutableSet<State> transitionsForProcess = ImmutableSet.of(NOTIFY);
    private static ImmutableSet<State> transitionsForNotify = ImmutableSet.of(CHECKADDRESS);

    private static ImmutableSet<State> transitionsForCheckAddress =
            ImmutableSet.of(RADRIATIONCONTROL, CHECKLEGALITY, CHECKFORDANGEROUS, SORTING);

    private static ImmutableSet<State> transitionsForRadiationControl =
            ImmutableSet.of(CHECKLEGALITY, CHECKFORDANGEROUS, SORTING);

    private static ImmutableSet<State> transitionsForCheckLegality =
            ImmutableSet.of(RADRIATIONCONTROL, CHECKFORDANGEROUS, SORTING);

    private static ImmutableSet<State> transitionsForCheckForDangerous =
            ImmutableSet.of(RADRIATIONCONTROL, CHECKLEGALITY, SORTING);

    private static ImmutableSet<State> transitionsForSorting = ImmutableSet.of(SENDING);

    static {
        routeMap.put(START, transitionsStart);
        routeMap.put(PROCESS, transitionsForProcess);
        routeMap.put(NOTIFY, transitionsForNotify);
        routeMap.put(CHECKADDRESS, transitionsForCheckAddress);
        routeMap.put(RADRIATIONCONTROL, transitionsForRadiationControl);
        routeMap.put(CHECKLEGALITY, transitionsForCheckLegality);
        routeMap.put(CHECKFORDANGEROUS, transitionsForCheckForDangerous);
        routeMap.put(SORTING, transitionsForSorting);
    }
}
