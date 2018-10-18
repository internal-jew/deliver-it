package com.alevel.deliverit.logistics.fsm;

import java.util.Map;
import java.util.Set;

import static com.alevel.deliverit.logistics.fsm.State.DEPARTED;
import static com.alevel.deliverit.logistics.fsm.State.LOST;
import static com.alevel.deliverit.logistics.fsm.State.TERMINAL;

/**
 * @author Vadym Mitin
 */
public class MonolithFinitStateMachine {
    private State currentState;
    private final Map<State, Set<State>> routeMap;

    public MonolithFinitStateMachine(Map<State, Set<State>> routeMap, State startState) {
        this.routeMap = routeMap;
        this.currentState = startState;
    }

    public void start(Context context) {
        while (!isTerminalState()) {
            Set<State> transitionStates = routeMap.get(currentState);
            for (State state : transitionStates) {

            }
        }
    }

    private void switchState(State state) {
        currentState = state;
    }

    private boolean isTerminalState() {
        return (currentState == TERMINAL) || (currentState == DEPARTED) || (currentState == LOST);
    }
}
