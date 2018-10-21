package com.alevel.deliverit.logistics.fsm;

import java.util.Set;

/**
 * http://smc.sourceforge.net/
 * https://habr.com/post/160105/
 *
 * @author Vadym Mitin
 */
public abstract class StateMachine {

    public abstract void switchState(State state);

    public abstract State getCurrentState();

    public abstract void restart();

    public abstract void handle();
}
