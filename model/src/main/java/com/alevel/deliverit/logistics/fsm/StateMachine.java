package com.alevel.deliverit.logistics.fsm;

import java.util.Set;

/**
 * http://smc.sourceforge.net/
 * https://habr.com/post/160105/
 *
 * @author Vadym Mitin
 */
public abstract class StateMachine {

    abstract void switchState(State state);

    public abstract State getCurrentState();

    abstract void restart();

    abstract void handle();
}
