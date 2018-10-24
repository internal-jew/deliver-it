package com.alevel.deliverit.customers.tracking.handler;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelReceipt;
import com.alevel.deliverit.logistics.clock.generator.ClockSignal;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.postal.network.PostNetwork;
import com.alevel.deliverit.postal.network.PostUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vadym Mitin
 */
@DisplayName("Handler should")
class ParcelHandingTest {

    @Test
    @DisplayName("send 2 parcels both ways")
    void doubleTest() {
        ParcelTrackingHandler handler = ParcelTrackingHandler.instance();
        ClockSignal signal = new ClockSignal(1L);

        PostUnit unit1 = PostNetwork.instance().findUnit(1L).get();
        PostUnit unit2 = PostNetwork.instance().findUnit(2L).get();
        PostUnit unit5 = PostNetwork.instance().findUnit(5L).get();
        PostUnit unit7 = PostNetwork.instance().findUnit(7L).get();
        PostUnit unit8 = PostNetwork.instance().findUnit(8L).get();
        PostUnit unit9 = PostNetwork.instance().findUnit(9L).get();

        ParcelReceipt receipt1 = ParcelHandingGiven.createReceipt();
        ParcelReceipt receipt2 = ParcelHandingGiven.createReverseReceipt();
        Parcel parcel1 = receipt1.getParcel();
        Parcel parcel2 = receipt2.getParcel();

        handler.handle(signal); // ACCEPTING
        assertEquals(parcel1, unit1.getParcelInProcessing().get());
        assertEquals(parcel2, unit8.getParcelInProcessing().get());
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        handler.handle(signal); // throw the parcel1 to the outgoing queue and receive the next parcel1 for processing
        assertEquals(parcel1, unit1.getOutgoingParcels().peek().getKey());
        assertEquals(parcel2, unit8.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // deliver the parcel1 to the next post office
        assertEquals(parcel1, unit2.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        assertEquals(parcel2, unit5.getParcelInProcessing().get()); //Очередь смещается из за того что после 8 юнита идет 5й, а он уже отработан
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, unit2.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // throw to outgoing queue
        assertEquals(parcel2, unit5.getOutgoingParcels().peek().getKey());
        assertEquals(parcel1, unit8.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        assertEquals(parcel2, unit7.getParcelInProcessing().get());
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, unit8.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // throw to outgoing queue
        assertEquals(parcel2, unit7.getOutgoingParcels().peek().getKey());
        assertEquals(parcel1, unit9.getParcelInProcessing().get());
        handler.handle(signal); // ACCEPTING
        handler.handle(signal); // WEIGHTING
        handler.handle(signal); // RADIATION_CONTROL
        handler.handle(signal); // STAMPING
        handler.handle(signal); // DEPARTED
        assertEquals(parcel1, unit9.getOutgoingParcels().peek().getKey());
        handler.handle(signal); // STANDBY
    }
}
