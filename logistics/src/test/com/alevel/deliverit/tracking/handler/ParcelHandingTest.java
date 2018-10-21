package com.alevel.deliverit.tracking.handler;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.fsm.FSMGiven;
import com.alevel.deliverit.gateway.RouteLookup;
import com.alevel.deliverit.logistics.fsm.State;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.PostNetwork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author Vadym Mitin
 */
@DisplayName("Handler should")
class ParcelHandingTest {

    @Test
    @DisplayName("receive parcels with post offices")
    void receivingTest() {
        Set<PostOffice> postOffices = PostNetwork.instance().getPostOffices();
        ParcelTrackingHandler handler = ParcelTrackingHandler.instance();
        postOffices.forEach(handler::registerPostOffices);

        Parcel parcel = FSMGiven.givenParcel();
        RouteLookupRequest routeLookupRequest = new RouteLookupRequest(parcel.getStartPostOfficeId(), parcel.getFinishPostOfficeId());
        Route route = RouteLookup.find(routeLookupRequest);

        handler.handle(parcel, route);
        ParcelInfo parcelInfo = handler.getParcelInfo(parcel);

        Long id = parcelInfo.getCurrentPostOfficeId();
        State state = parcelInfo.getCurrentState();

        Assertions.assertEquals(route.getUnits().get(0).getId().getValue(), id);

    }
}
