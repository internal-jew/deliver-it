package com.alevel.deliverit.postal.network.limitations;

import com.alevel.deliverit.postal.network.SendingContext;

/**
 * @author Sergey Bogovesov
 */
public class SimpleLimitation extends Limitation {
    private int weight;

    public SimpleLimitation(int weight) {
        this.weight = weight;
    }

    @Override
    public int affectWeight(int weight, SendingContext sendingContext) {
        return this.weight;
    }
}
