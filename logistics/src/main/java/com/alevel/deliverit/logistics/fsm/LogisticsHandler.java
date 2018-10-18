package com.alevel.deliverit.logistics.fsm;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.postal.network.PostOffice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Vadym Mitin
 */
public class LogisticsHandler {
    private Map<PostOffice, Set<Parcel>> parcelRepository = new HashMap<>();

}
