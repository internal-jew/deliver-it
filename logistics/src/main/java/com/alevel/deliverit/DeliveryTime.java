package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Sergey Bogovesov
 */
public class DeliveryTime {

    public EstimatedDeliveryTime estimate(Parcel parcel, Route route) {
        //TODO https://github.com/internal-jew/deliver-it/issues/12
//        System.out.println("`DeliveryTime.estimate` is not yet implemented");

        double postOfficeDelay = estimatePostOfficeDelay(route);
        double distance = estimateDistanceDelay(route);


        long delay = Double.valueOf(postOfficeDelay + distance).longValue();
        LocalDate estimatedData = LocalDate.now().plusDays(delay);

        return new EstimatedDeliveryTime(estimatedData);
    }

    /**
     * @param route
     * @return the number of days spent in travel
     */
    private double estimateDistanceDelay(Route route) {
        //turning abstract route weight to concrete kilometers
        int distanceKilometers = route.getWeight() * 1000;

        // in km/hour
        double averageSpeed = 60;

        return (distanceKilometers / averageSpeed) / 24;
    }


    /**
     * @param route
     * @return the number of days spent in post offices
     */
    private double estimatePostOfficeDelay(Route route) {
        List<PostOffice> units = route.getUnits();

        //TODO implement a different number of inspections in offices
        return units.size() * 0.8;

    }
}
