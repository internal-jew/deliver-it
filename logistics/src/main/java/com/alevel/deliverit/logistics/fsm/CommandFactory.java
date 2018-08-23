package com.alevel.deliverit.logistics.fsm;

import com.google.common.base.Optional;

abstract class CommandFactory {

    public abstract Optional<Command> getCommand(State currentState, Context context);
//    {
//        Set<State> transitions = getTransitionMap(currentState);
//        Command command = new Command();
//        for (State state : transitions) {
//            //logic for returning command
//        }
//        return Optional.of(command);
//    }

}


