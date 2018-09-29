package com.alevel.deliverit.logistics.postal.network.constraint;

import com.alevel.deliverit.logistics.postal.network.context.SendingContext;

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
