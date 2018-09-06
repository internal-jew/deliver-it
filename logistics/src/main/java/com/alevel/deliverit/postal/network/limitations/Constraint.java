package com.alevel.deliverit.postal.network.limitations;

import com.alevel.deliverit.postal.network.SendingContext;

/**
 * @author Sergey Bogovesov
 */
public abstract class Constraint {

    public abstract int affectWeight(int weight, SendingContext sendingContext);
}
