package com.alevel.deliverit.logistics.fsm;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.postal.network.Route;

import java.util.Map;
import java.util.Set;

/**
 * http://smc.sourceforge.net/
 * https://habr.com/post/160105/
 *
 * @author Vadym Mitin
 */
public abstract class StateMachine {
    State currentState;

    abstract void setStates(Set<State> states);

    abstract void setParcelMap(Map<Parcel, State> parcelMap);

    abstract void setParcelRoutes(Map<Parcel, Route> routeMap);

    abstract void changeState(Parcel parcel, State state);
}
