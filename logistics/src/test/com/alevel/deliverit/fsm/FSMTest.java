package com.alevel.deliverit.fsm;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.clock.generator.ClockSignal;
import com.alevel.deliverit.logistics.fsm.State;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.postal.network.PostNetwork;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vadym Mitin
 */
@DisplayName("FSM should")
public class FSMTest {
    @Test
    @DisplayName("Change state if receive signal")
    void test() {
        ClockSignal signal = new ClockSignal();
        PostOffice postOffice = PostNetwork.instance().find(1L).get();

        Parcel parcel = FSMGiven.givenParcel();
        postOffice.receiveParcel(parcel);
        Parcel parcel1 = FSMGiven.givenParcel2();
        postOffice.receiveParcel(parcel1);


        postOffice.activate(signal);
        assertEquals(State.TERMINAL, postOffice.getParcelsInProcessing().get(parcel));


        postOffice.activate(signal);
        assertEquals(State.ACCEPTING, postOffice.getParcelsInProcessing().get(parcel));
        assertEquals(State.TERMINAL, postOffice.getParcelsInProcessing().get(parcel1));
    }
}
