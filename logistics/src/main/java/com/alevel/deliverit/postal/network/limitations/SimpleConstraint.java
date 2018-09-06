package com.alevel.deliverit.postal.network.limitations;

import com.alevel.deliverit.postal.network.SendingContext;

/**
 * @author Sergey Bogovesov
 */
public class SimpleConstraint extends Constraint {
    private int weight;

    @Override
    public int affectWeight(int weight, SendingContext sendingContext) {
        return this.weight;
    }

    public SimpleConstraint(int weight) {
        this.weight = weight;
    }
}
