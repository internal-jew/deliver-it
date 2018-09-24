package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.gateway.RouteLookup;
import com.alevel.deliverit.logistics.postal.network.Route;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsGateway {
    public static Route find(RouteLookupRequest request) {
        //TODO add vertex magic
        return RouteLookup.find(request);
    }
}
