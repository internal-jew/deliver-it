package com.alevel.deliverit.logistics.postal.network.constraint;

import com.alevel.deliverit.logistics.postal.network.context.SendingContext;

import java.io.Serializable;

/**
 * @author Sergey Bogovesov
 */
public abstract class Constraint implements Serializable {

    public abstract int affectWeight(int weight, SendingContext sendingContext);
}
