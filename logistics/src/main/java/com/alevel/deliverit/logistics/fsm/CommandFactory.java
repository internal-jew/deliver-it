package com.alevel.deliverit.logistics.fsm;


import java.util.Optional;

/**
 * @author Vitalii Usatyi
 */
public abstract class CommandFactory<E extends Enum<E>, C extends Context> {

    public abstract Optional<Command> getCommand(E currentState, C context);
}
