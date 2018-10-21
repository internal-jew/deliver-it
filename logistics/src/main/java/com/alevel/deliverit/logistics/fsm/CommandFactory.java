package com.alevel.deliverit.logistics.fsm;


import com.google.common.base.Optional;

/**
 * @author Vitalii Usatyi
 */
public abstract class CommandFactory<E extends Enum<E>> {

    public abstract Optional<Command> getCommand(E currentState, Context context);
}
