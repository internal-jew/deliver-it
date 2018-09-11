package com.alevel.deliverit.postal.network.constraint;

import com.alevel.deliverit.postal.network.context.SendingContext;

/**
 * @author Sergey Bogovesov
 */
public abstract class Constraint {

    public abstract int affectWeight(int weight, SendingContext sendingContext);
}
