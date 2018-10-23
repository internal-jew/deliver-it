package com.alevel.deliverit.logistics.fsm;

/**
 * http://smc.sourceforge.net/
 * https://habr.com/post/160105/
 *
 * @author Vadym Mitin
 */
public interface FiniteStateMachine<E extends Enum<E>, C extends Context> {
    //    FiniteStateMachine(CommandFactory<E, C> commandFactory, Map<E, Set<E>> routeMap, E startState);
    E getCurrentState();

    void start(C context);
}
