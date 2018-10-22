package com.alevel.deliverit.customers.tracking.handler;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.logistics.clock.generator.ClockSignal;
import com.alevel.deliverit.logistics.fsm.State;
import com.alevel.deliverit.logistics.postal.network.Pair;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.PostNetwork;

import java.util.*;

/**
 * @author Vadym Mitin
 */
public class ParcelTrackingHandler {

    private final Set<ParcelReceipt> parcelReceipts = new HashSet<>();
    private final Set<PostOffice> postOffices = PostNetwork.instance().getPostOffices();
    private final Map<Parcel, PostOffice> parcelRepository = new HashMap<>();

    private ParcelTrackingHandler() {
    }

    public static ParcelTrackingHandler instance() {
        return Singleton.INSTANCE.instance;
    }

    public void handle(ClockSignal signal) throws IllegalStateException {
        for (PostOffice office : postOffices) {
            Queue<Pair<Parcel, Route>> outgoingParcels = office.getOutgoingParcels();
            if (!outgoingParcels.isEmpty()) {
                PostOffice nextOffice = outgoingParcels.peek().getValue().findNext(office);
                if (!nextOffice.equals(office)) {
                    Pair<Parcel, Route> remove = outgoingParcels.remove();
                    nextOffice.addParcel(remove.getKey(), remove.getValue());
                } else delivered(outgoingParcels.peek().getKey(), office);
            }
            office.activate(signal);
        }
    }

    private void delivered(Parcel key, PostOffice office) {
        System.out.println("The Parcel: " + key.getId().getValue()
                + " wait for consumer on the postal office: "
                + office.getId().getValue());
    }

    public State getcurrentState(Parcel parcel) {
        return parcelRepository.get(parcel).getCurrentState();
    }

    public void registerReceipt(ParcelReceipt receipt) {
        parcelReceipts.add(receipt);
        Parcel parcel = receipt.getParcel();
        Route route = receipt.getRoute();
        PostOffice postOffice = route.getUnits().get(0);
        postOffice.addParcel(parcel, route);
        parcelRepository.put(parcel, postOffice);
    }


    private enum Singleton {
        INSTANCE;
        private final ParcelTrackingHandler instance = new ParcelTrackingHandler();
    }

}
