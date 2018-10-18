package com.alevel.deliverit.logistics.fsm;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Set;

import static com.alevel.deliverit.logistics.fsm.State.*;

/**
 * @author Vitalii Usatyi
 */
public class FiniteStateMachine {
    private State currentState;
    private final ImmutableMap<State, Set<State>> routeMap;
    private final CommandFactory commandFactory;

    public FiniteStateMachine(CommandFactory commandFactory, Map<State, Set<State>> routeMap, State startState) {
        this.commandFactory = commandFactory;
        this.routeMap = ImmutableMap.copyOf(routeMap);
        this.currentState = startState;
    }

    public void start(Context context) {
        while (!isTerminalState()) {
            Set<State> transitionStates = routeMap.get(currentState);
            for (State state : transitionStates) {
                Optional<Command> command = commandFactory.getCommand(state, context);
                if (command.isPresent()) {
                    CommandExecutor.execute(command.get(), context);
                    switchState(state);
                }
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
