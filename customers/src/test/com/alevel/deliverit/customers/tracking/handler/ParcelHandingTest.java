package com.alevel.deliverit.customers.tracking.handler;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.logistics.clock.generator.ClockSignal;
import com.alevel.deliverit.logistics.fsm.State;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;
import com.alevel.deliverit.postal.network.PostNetwork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.alevel.deliverit.logistics.fsm.State.ACCEPTING;
import static com.alevel.deliverit.logistics.fsm.State.TERMINAL;
import static com.alevel.deliverit.logistics.fsm.State.WEIGHTING;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vadym Mitin
 */
@DisplayName("Handler should")
class ParcelHandingTest {

    @Test
    @DisplayName("receive parcels with post offices")
    void receivingTest() {
        ParcelTrackingHandler handler = ParcelTrackingHandler.instance();
        ClockSignal signal = new ClockSignal(1L);

        PostOffice office1 = PostNetwork.instance().find(1L).get();
        PostOffice office2 = PostNetwork.instance().find(2L).get();
        PostOffice office8 = PostNetwork.instance().find(8L).get();
        PostOffice office9 = PostNetwork.instance().find(9L).get();

        ParcelReceipt receipt1 = ParcelHandingGiven.createReceipt();
        Parcel parcel1 = receipt1.getParcel();

        assertEquals(parcel1, office1.getIncomingParcels().peek().getKey());

        handler.handle(signal); // ACCEPTING
        assertEquals(parcel1, office1.getParcelInProcessing().get());
        assertEquals(ACCEPTING, office1.getCurrentState());
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        handler.handle(signal); // throw the parcel1 to the outgoing queue and receive the next parcel1 for processing
        assertEquals(parcel1, office1.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // deliver the parcel1 to the next post office
        assertEquals(parcel1, office2.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, office2.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // throw to outgoing queue
        assertEquals(parcel1, office8.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, office8.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // throw to outgoing queue
        assertEquals(parcel1, office9.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, office9.getOutgoingParcels().peek().getKey());

        assertThrows(IllegalStateException.class, () -> handler.handle(signal));
    }

    @Test
    @DisplayName("receive parcels in reverse order")
    void reverseTest() {
        ParcelTrackingHandler handler = ParcelTrackingHandler.instance();
        ClockSignal signal = new ClockSignal(1L);

        PostOffice office1 = PostNetwork.instance().find(8L).get();
        PostOffice office2 = PostNetwork.instance().find(5L).get();
        PostOffice office3 = PostNetwork.instance().find(7L).get();

        ParcelReceipt receipt1 = ParcelHandingGiven.createReverseReceipt();
        Parcel parcel1 = receipt1.getParcel();

        assertEquals(parcel1, office1.getIncomingParcels().peek().getKey());

        handler.handle(signal); // ACCEPTING
        assertEquals(parcel1, office1.getParcelInProcessing().get());
        assertEquals(ACCEPTING, office1.getCurrentState());
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        handler.handle(signal); // throw the parcel1 to the outgoing queue and receive the next parcel1 for processing
        assertEquals(parcel1, office1.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // deliver the parcel1 to the next post office
        assertEquals(parcel1, office2.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, office2.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // throw to outgoing queue
        assertEquals(parcel1, office3.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, office3.getOutgoingParcels().peek().getKey());

        assertThrows(IllegalStateException.class, () -> handler.handle(signal));
    }

    @Test
    @DisplayName("receive parcels in reverse order")
    void doubleTest() {
        ParcelTrackingHandler handler = ParcelTrackingHandler.instance();
        ClockSignal signal = new ClockSignal(1L);

        PostOffice office1 = PostNetwork.instance().find(1L).get();
        PostOffice office2 = PostNetwork.instance().find(2L).get();
        PostOffice office5 = PostNetwork.instance().find(5L).get();
        PostOffice office7 = PostNetwork.instance().find(7L).get();
        PostOffice office8 = PostNetwork.instance().find(8L).get();
        PostOffice office9 = PostNetwork.instance().find(9L).get();

        ParcelReceipt receipt1 = ParcelHandingGiven.createReceipt();
        ParcelReceipt receipt2 = ParcelHandingGiven.createReverseReceipt();
        Parcel parcel1 = receipt1.getParcel();
        Parcel parcel2 = receipt2.getParcel();

        assertEquals(parcel1, office1.getIncomingParcels().peek().getKey());

        handler.handle(signal); // ACCEPTING
        assertEquals(parcel1, office1.getParcelInProcessing().get());
        assertEquals(ACCEPTING, office1.getCurrentState());
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        handler.handle(signal); // throw the parcel1 to the outgoing queue and receive the next parcel1 for processing
        assertEquals(parcel1, office1.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // deliver the parcel1 to the next post office
        assertEquals(parcel1, office2.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, office2.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // throw to outgoing queue
        assertEquals(parcel1, office3.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, office3.getOutgoingParcels().peek().getKey());

        assertThrows(IllegalStateException.class, () -> handler.handle(signal));
    }
}
