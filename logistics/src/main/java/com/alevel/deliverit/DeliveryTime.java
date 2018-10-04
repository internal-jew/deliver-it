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

    static final double AVERAGE_SPEED = 60;
    static final int KILOMETERS_KOEFFICIENT = 1000;
    static final int DAY_HOURS = 24;
    static final double DELAY_COEFFICIENT = 0.8;

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
        int distanceKilometers = route.getWeight() * KILOMETERS_KOEFFICIENT;

        return (distanceKilometers / AVERAGE_SPEED) / DAY_HOURS;
    }


    /**
     * @param route
     * @return the number of days spent in post offices
     */
    private double estimatePostOfficeDelay(Route route) {
        List<PostOffice> units = route.getUnits();

        //TODO implement a different number of inspections in offices
        return units.size() * DELAY_COEFFICIENT;

    }
}
