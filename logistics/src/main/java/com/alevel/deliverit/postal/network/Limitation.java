package com.alevel.deliverit.postal.network;

/**
 * @author Sergey Bogovesov
 */
public abstract class Limitation {

    public int affectWeight(int weight, SendingContext sendingContext){
        return weight;
    }
}
