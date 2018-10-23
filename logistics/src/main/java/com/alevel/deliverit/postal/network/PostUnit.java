package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.logistics.fsm.*;
import com.alevel.deliverit.logistics.postal.network.PostOffice;

/**
 * @author Vadym Mitin
 */
public class PostUnit {
    private final FiniteStateMachine<State, LogisticContext> finiteStateMachine =
            new FiniteStateMachine(new LogisticCommandFactory(),
                    RouteMap.routeMap,
                    State.START);
    private final PostOffice postOffice;

    public PostUnit(PostOffice postOffice) {
        this.postOffice = postOffice;
    }
}
