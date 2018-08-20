package com.alevel.stateMachine;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.*;

import static com.alevel.stateMachine.State.*;

class RoteMap {
    static Map<State, ImmutableSet<State>> roteMap = new HashMap<State, ImmutableSet<State>>();
    private static ImmutableSet<State> statesForStart = ImmutableSet.of(REGISTER, CHECKING, BILLING, SORTING, KICKVERYHARD, STAMPING);
    private static ImmutableSet<State> statesForRegister = ImmutableSet.of(CHECKING, SORTING, BILLING, KICKVERYHARD, STAMPING);
    private static ImmutableSet<State> statesForChecking = ImmutableSet.of(SORTING, BILLING, KICKVERYHARD, STAMPING);
    private static ImmutableSet<State> statesForSorting = ImmutableSet.of(BILLING, KICKVERYHARD, STAMPING);
    private static ImmutableSet<State> statesForBilling = ImmutableSet.of(KICKVERYHARD, STAMPING, STAMPING);
    private static ImmutableSet<State> statesForKicking = ImmutableSet.of(STAMPING);
    private static ImmutableSet<State> statesForStamping = ImmutableSet.of(SENDING);
    private static ImmutableSet<State> statesForSanding = ImmutableSet.of(TERMINAL);

    static {
        roteMap.put(START, statesForStart);
        roteMap.put(REGISTER, statesForRegister);
        roteMap.put(CHECKING, statesForChecking);
        roteMap.put(SORTING, statesForSorting);
        roteMap.put(BILLING, statesForBilling);
        roteMap.put(KICKVERYHARD, statesForKicking);
        roteMap.put(STAMPING, statesForStamping);
        roteMap.put(SENDING, statesForSanding);
    }
}
