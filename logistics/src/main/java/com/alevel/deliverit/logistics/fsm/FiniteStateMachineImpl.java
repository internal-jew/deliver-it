package com.alevel.deliverit.logistics.fsm;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Vitalii Usatyi
 */
public class FiniteStateMachineImpl<E extends Enum<E>, C extends Context> implements FiniteStateMachine<E, C> {
    private E currentState;
    private final ImmutableMap<E, Set<E>> routeMap;
    private final CommandFactory<E, C> commandFactory;

    public FiniteStateMachineImpl(CommandFactory<E, C> commandFactory, Map<E, ImmutableSet<E>> routeMap, E startState) {
        this.commandFactory = commandFactory;
        this.routeMap = ImmutableMap.copyOf(routeMap);
        this.currentState = startState;
    }

    public void start(C context) {
        if (isTerminalState()) {
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
        return routeMap.containsKey(currentState);
    }

    private void switchState(E state) {
        currentState = state;
        System.out.println(state);
    }

    @Override
    public E getCurrentState() {
        return currentState;
    }

}
