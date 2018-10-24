package com.alevel.deliverit.fsm;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.clock.generator.ClockSignal;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.PostNetwork;
import com.alevel.deliverit.postal.network.PostUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static com.alevel.deliverit.logistics.fsm.State.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vadym Mitin
 */
@DisplayName("FSM should")
public class oldFSMTest {
    @Test
    @DisplayName("take the parcel from the inbox, process it and throw it into the outgoing box")
    void test() {
        int i = 0;
        ClockSignal signal = new ClockSignal(0L);
        PostUnit postOffice = PostNetwork.instance().findUnit(1L).get();

//        State[] states = {STANDBY, ACCEPTING, WEIGHTING, RADIATION_CONTROL, STAMPING, DEPARTED};

        Parcel parcel1 = FSMGiven.givenParcel();
        Route route1 = FSMGiven.givenRoute1();
        Parcel parcel2 = FSMGiven.givenParcel2();
        Route route2 = FSMGiven.givenRoute2();

        postOffice.addParcel(parcel1, route1);

        assertEquals(parcel1, postOffice.getIncomingParcels().peek().getKey());

        assertEquals(Optional.empty(), postOffice.getParcelInProcessing());
        assertEquals(STANDBY, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        assertEquals(parcel1, postOffice.getParcelInProcessing().get());
        assertEquals(ACCEPTING, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        assertEquals(parcel1, postOffice.getParcelInProcessing().get());
        assertEquals(WEIGHTING, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        assertEquals(parcel1, postOffice.getParcelInProcessing().get());
        assertEquals(RADIATION_CONTROL, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        assertEquals(parcel1, postOffice.getParcelInProcessing().get());
        assertEquals(STAMPING, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);

        assertEquals(parcel1, postOffice.getParcelInProcessing().get());
        assertEquals(DEPARTED, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        assertEquals(parcel1, postOffice.getOutgoingParcels().peek().getKey());
        assertEquals(STANDBY, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        postOffice.activate(signal);
        assertEquals(STANDBY, postOffice.getCurrentState());
        System.out.println("activate: " + i++);


        postOffice.addParcel(parcel2, route2);
        assertEquals(parcel2, postOffice.getIncomingParcels().peek().getKey());
        assertEquals(STANDBY, postOffice.getCurrentState());

        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        assertEquals(parcel2, postOffice.getParcelInProcessing().get());
        assertEquals(ACCEPTING, postOffice.getCurrentState());
        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        assertEquals(0, postOffice.getIncomingParcels().size());
        postOffice.addParcel(parcel1, route1);
        postOffice.addParcel(parcel1, route1);
        postOffice.addParcel(parcel1, route1);
        assertEquals(3, postOffice.getIncomingParcels().size());
        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        postOffice.activate(signal);
        System.out.println("activate: " + i++);
        postOffice.activate(signal);
        System.out.println("activate: " + i++);

    }

}
