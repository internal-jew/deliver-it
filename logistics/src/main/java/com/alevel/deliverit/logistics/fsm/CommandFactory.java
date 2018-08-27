package com.alevel.deliverit.logistics.fsm;

import com.google.common.base.Optional;

abstract class CommandFactory {

    public abstract Optional<Command> getCommand(State currentState, Context context);
}
