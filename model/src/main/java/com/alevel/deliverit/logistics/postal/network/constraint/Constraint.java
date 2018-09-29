package com.alevel.deliverit.logistics.postal.network.constraint;

import com.alevel.deliverit.logistics.postal.network.context.SendingContext;

/**
 * @author Sergey Bogovesov
 */
public abstract class Constraint {

    public abstract int affectWeight(int weight, SendingContext sendingContext);
}
