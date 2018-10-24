package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.clock.generator.ClockSignal;
import com.alevel.deliverit.logistics.fsm.*;
import com.alevel.deliverit.logistics.postal.network.Pair;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import static com.alevel.deliverit.logistics.fsm.State.*;

/**
 * @author Vadym Mitin
 */
public class PostUnit {
    private final PostOffice postOffice;
    private final Queue<Pair<Parcel, Route>> incomingParcels = new PriorityQueue<>();
    private final Queue<Pair<Parcel, Route>> outgoingParcels = new PriorityQueue<>();
    private Parcel parcelInProcessing;
    private Route currentRoute;
    private final LogisticContext context = new LogisticContext();

    public PostUnit(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    public LogisticContext getContext() {
        return context;
    }

    public void activate(ClockSignal signal) {

        FiniteStateMachine stateMachine = postOffice.getStateMachine();
        if (stateMachine.getCurrentState().equals(DEPARTED)) {
            departingParcel();
            if (parcelInProcessing == null) {
                stateMachine.start(context);
            }
        }
        if (stateMachine.getCurrentState().equals(STANDBY)) {
            enqueueParcel();
        }
        if (parcelInProcessing != null) {
            stateMachine.start(context);
        }
    }

    private void enqueueParcel() {
        if (!incomingParcels.isEmpty()) {
            Pair<Parcel, Route> pair = incomingParcels.remove();
            parcelInProcessing = pair.getKey();
            currentRoute = pair.getValue();
        }
    }

    private void departingParcel() {
        Pair<Parcel, Route> pair = new Pair<>(parcelInProcessing, currentRoute);
        outgoingParcels.add(pair);
        parcelInProcessing = null;
        currentRoute = null;
    }

    public Optional<Parcel> getParcelInProcessing() {
        return Optional.ofNullable(parcelInProcessing);
    }

    public void addParcel(Parcel parcel, Route route) {
        Pair<Parcel, Route> pair = new Pair<>(parcel, route);
        incomingParcels.add(pair);
    }

    public Queue<Pair<Parcel, Route>> getIncomingParcels() {
        return incomingParcels;
    }

    public Queue<Pair<Parcel, Route>> getOutgoingParcels() {
        return outgoingParcels;
    }

    public State getCurrentState() {
        return postOffice.getCurrentState();
    }
}
