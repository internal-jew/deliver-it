package com.alevel.stateMachine;
/**
 * @autor Vitalii Usatyi
 */

import com.alevel.stateMachine.com.alevel.premise.Parcel;
import com.google.common.collect.ImmutableSet;

import java.util.Map;

import static com.alevel.stateMachine.FiniteStateMachineAlgorithm.*;
import static com.alevel.stateMachine.State.START;
import static com.alevel.stateMachine.State.TERMINAL;

public class FiniteStateMachine {
    static State currentState = START;
    final static Map<State, ImmutableSet<State>> roteMap = RoteMap.roteMap;

    public FiniteStateMachine() {

    }

    public static FiniteStateMachine instance() {
        return Singleton.VALUE.value;
    }

    private enum Singleton {
        VALUE;
        private FiniteStateMachine value = new FiniteStateMachine();


    }

    public static void start(Parcel parcel) {
        while (currentState != TERMINAL) {
            System.out.println("!!! CURRENT STATE :" + currentState.toString() + " !!!!!!");
            runCommand(currentState, parcel);
        }
    }


}
