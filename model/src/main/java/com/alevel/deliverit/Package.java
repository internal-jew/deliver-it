package com.alevel.deliverit;

public class Package extends Entity<WeightId>{
    private final Weight weight;

    protected Package(WeightId id, Weight weight) {
        super(id);
        this.weight = weight;
    }
}
