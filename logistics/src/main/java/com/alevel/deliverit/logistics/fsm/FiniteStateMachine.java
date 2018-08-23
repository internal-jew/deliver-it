package com.alevel.deliverit.logistics.fsm;

/**
 * @autor Vitalii Usatyi
 */

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

import static com.alevel.deliverit.logistics.fsm.State.*;


public class FiniteStateMachine {
    private static State currentState = START;
    private final ImmutableMap<State, Set<State>> routeMap;
    private final CommandFactory commandFactory;

    public FiniteStateMachine(CommandFactory commandFactory, Map<State, Set<State>> routeMap) {
        this.commandFactory = commandFactory;
        this.routeMap = ImmutableMap.copyOf(routeMap);
    }

    public void start(Context context) throws CommandIsNotExists {
        while (!isTerminalState()) {
            ImmutableSet<State> transitionStates = (ImmutableSet<State>) routeMap.get(currentState);
            for (State state : transitionStates) {
                Optional<Command> command = commandFactory.getCommand(state, context);
                if (command.isPresent()) {
                    CommandExecutor.execute(command.get(), context);
                    switchCurrentState(state);
                } else {
                    throw new CommandIsNotExists();
                }
            }
//            System.out.println("!!! CURRENT STATE :" + currentState.toString() + " !!!!!!");
        }
    }

    private void switchCurrentState(State state) {
        currentState = state;
    }

    private static boolean isTerminalState() {
        if ((currentState == TERMINAL) || (currentState == DEPARTED) || (currentState == LOST)) {
            return true;
        } else {
            return false;
        }
    }
}
