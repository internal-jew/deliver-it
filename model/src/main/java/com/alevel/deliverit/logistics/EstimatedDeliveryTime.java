package com.alevel.deliverit.logistics;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Vadym Mitin
 */
public class EstimatedDeliveryTime implements Serializable {
    private final LocalDate estimatedDeliveryTime;

    public EstimatedDeliveryTime(LocalDate estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public LocalDate getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void print() {
        System.out.println(estimatedDeliveryTime.toString());
    }
}
