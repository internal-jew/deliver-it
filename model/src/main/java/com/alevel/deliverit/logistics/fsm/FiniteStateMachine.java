package com.alevel.deliverit.logistics.fsm;

/**
 *
 * @author Vadym Mitin
 */
public interface FiniteStateMachine<E extends Enum<E>, C extends Context> {
    E getCurrentState();

    void start(C context);
}
