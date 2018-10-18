package com.alevel.deliverit.logistics.fsm;

/**
 * @autor Vitalii Usatyi
 */

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Set;

/**
 * @author Vitalii Usatyi
 */
public class FiniteStateMachine<E extends Enum<E>> {
    private E currentState;
    private final ImmutableMap<E, Set<E>> routeMap;
    private final CommandFactory commandFactory;

    public FiniteStateMachine(CommandFactory commandFactory, Map<E, Set<E>> routeMap, E startState) {
        this.commandFactory = commandFactory;
        this.routeMap = ImmutableMap.copyOf(routeMap);
        this.currentState = startState;
    }

    public void start(Context context) {
        while (routeMap.containsKey(currentState)) {
            Set<E> transitionStates = routeMap.get(currentState);
            for (E state : transitionStates) {
                Optional<Command> command = commandFactory.getCommand(state, context);
                if (command.isPresent()) {
                    CommandExecutor.execute(command.get(), context);
                    switchState(state);
                }
            }
        }
    }

    private void switchState(E state) {
        currentState = state;
    }
}
