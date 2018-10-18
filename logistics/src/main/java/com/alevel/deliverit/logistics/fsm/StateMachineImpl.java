package com.alevel.deliverit.logistics.fsm;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.postal.network.Route;

import java.util.Map;
import java.util.Set;

import static com.alevel.deliverit.logistics.fsm.State.DEPARTED;
import static com.alevel.deliverit.logistics.fsm.State.LOST;
import static com.alevel.deliverit.logistics.fsm.State.TERMINAL;

/**
 * @author Vadym Mitin
 */
public class StateMachineImpl extends StateMachine {
    private State currentState;
    private Map<State, Set<State>> routeMap;

    private boolean isTerminalState() {
        return (currentState == TERMINAL) || (currentState == DEPARTED) || (currentState == LOST);
    }

    @Override
    void setStates(Set<State> states) {

    }

    @Override
    void setParcelMap(Map<Parcel, State> parcelMap) {

    }

    @Override
    void setParcelRoutes(Map<Parcel, Route> routeMap) {

    }

    @Override
    void changeState(Parcel parcel, State state) {

    }
}
