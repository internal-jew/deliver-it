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

/**
 * @author Vadym Mitin
 */
public class PostUnit {
    private final PostOffice postOffice;
    private final Queue<Pair<Parcel, Route>> incomingParcels = new PriorityQueue<>();
    private final Queue<Pair<Parcel, Route>> outgoingParcels = new PriorityQueue<>();
    private final FiniteStateMachine stateMachine;
    private Parcel parcelInProcessing;
    private Route currentRoute;
    private final LogisticContext context = new LogisticContext();

    public PostUnit(PostOffice postOffice) {
        this.postOffice = postOffice;
        this.stateMachine = postOffice.getStateMachine();
    }

    public void activate(ClockSignal signal) {
        if (stateMachine.getCurrentState().equals(State.DEPARTED)) {
            departingParcel();
            stateMachine.start(context);
        }
        if (stateMachine.getCurrentState().equals(State.TERMINAL)) {
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
}
