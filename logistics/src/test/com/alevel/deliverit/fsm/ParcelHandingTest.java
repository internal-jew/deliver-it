package com.alevel.deliverit.fsm;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.gateway.RouteLookup;
import com.alevel.deliverit.logistics.fsm.LogisticsHandler;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.PostNetwork;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author Vadym Mitin
 */
@DisplayName("Handler should")
public class ParcelHandingTest {

    @Test
    @DisplayName("receive parcels with post offices")
    void receivingTest() {
        Set<PostOffice> postOffices = PostNetwork.instance().getPostOffices();
        LogisticsHandler handler = LogisticsHandler.instance();
        postOffices.forEach((postOffice) -> handler.registerPostOffices(postOffice));

        Parcel parcel = FSMGiven.givenParcel();
        RouteLookupRequest routeLookupRequest = new RouteLookupRequest(parcel.getStartPostOfficeId(), parcel.getFinishPostOfficeId());
        Route route = RouteLookup.find(routeLookupRequest);


        handler.recieveParcelWithRoute(parcel, route);

    }
}
