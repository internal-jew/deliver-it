package com.alevel.deliverit.customers.tracking.handler;

import com.alevel.deliverit.BusinessLogicService;
import com.alevel.deliverit.Subscribe;
import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.logistics.clock.generator.ClockSignal;
import com.alevel.deliverit.logistics.fsm.State;
import com.alevel.deliverit.logistics.postal.network.Pair;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.PostNetwork;
import com.alevel.deliverit.postal.network.PostUnit;

import java.util.*;

/**
 * @author Vadym Mitin
 */
public class ParcelTrackingHandler implements BusinessLogicService {

    private final Set<ParcelReceipt> parcelReceipts = new HashSet<>();
    //    private final Set<PostOffice> postOffices = PostNetwork.instance().getPostOffices();
    private final Set<PostUnit> postUnits = PostNetwork.instance().getPostUnits();
    private final Map<Parcel, PostOffice> parcelRepository = new HashMap<>();
//    private final Map<PostUnit, PostOffice> units = PostNetwork.instance().getUnits();

    private ParcelTrackingHandler() {

    }

    public static ParcelTrackingHandler instance() {
        return Singleton.INSTANCE.instance;
    }

    @Subscribe("parcel.tracking.service")
    public void handle(ClockSignal signal) throws IllegalStateException {
        for (PostUnit unit : postUnits) {
            Queue<Pair<Parcel, Route>> outgoingParcels = unit.getOutgoingParcels();
            if (!outgoingParcels.isEmpty()) {
                PostOffice nextOffice = outgoingParcels.peek().getValue().findNext(unit.getPostOffice());
                PostUnit nextUnit = PostNetwork.instance().findUnit(nextOffice.getId().getValue()).get();
                if (!nextUnit.equals(unit)) {
                    Pair<Parcel, Route> remove = outgoingParcels.remove();
                    nextUnit.addParcel(remove.getKey(), remove.getValue());
                    parcelRepository.put(remove.getKey(), unit.getPostOffice());
                } else delivered(outgoingParcels.peek().getKey(), unit.getPostOffice());
            }
            unit.activate(signal);
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
        PostUnit postUnit = PostNetwork.instance().findUnit(postOffice.getId().getValue()).get();
        postUnit.addParcel(parcel, route);
        parcelRepository.put(parcel, postOffice);
    }

    private enum Singleton {
        INSTANCE;
        private final ParcelTrackingHandler instance = new ParcelTrackingHandler();
    }

}
