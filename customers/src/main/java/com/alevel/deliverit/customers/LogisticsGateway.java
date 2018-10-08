package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.postal.network.Route;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsGateway {
    public static Route find(RouteLookupRequest request) {
        Future<Route> route = Future.future();
        VertxContext.instance().eventBus().send("logistics.calculate.distance", request, reply -> {
            if (reply.succeeded()) {
                route.complete((Route) reply.result().body());
            } else {
                throw new IllegalStateException("LogisticsGateway Error");
            }
        });
        return route.result();
    }
}