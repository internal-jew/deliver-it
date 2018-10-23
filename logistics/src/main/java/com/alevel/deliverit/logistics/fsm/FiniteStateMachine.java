package com.alevel.deliverit.logistics.fsm;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Vitalii Usatyi
 */
public class FiniteStateMachine<E extends Enum<E>, C extends Context> {
    private E currentState;
    private final ImmutableMap<E, Set<E>> routeMap;
    private final CommandFactory<E, C> commandFactory;

    public FiniteStateMachine(CommandFactory<E, C> commandFactory, Map<E, Set<E>> routeMap, E startState) {
        this.commandFactory = commandFactory;
        this.routeMap = ImmutableMap.copyOf(routeMap);
        this.currentState = startState;
    }

    public void start(C context) {
        while (!isTerminalState()) {
            Set<E> transitionStates = routeMap.get(currentState);
            for (E state : transitionStates) {
                Optional<Command<C>> command = commandFactory.getCommand(state, context);
                if (command.isPresent()) {
                    CommandExecutor.execute(command.get(), context);
                    switchState(state);
                    break;
                }
            }
        }
    }

    private boolean isTerminalState() {
        return !routeMap.containsKey(currentState);
    }

    private void switchState(E state) {
        currentState = state;
    }

}
