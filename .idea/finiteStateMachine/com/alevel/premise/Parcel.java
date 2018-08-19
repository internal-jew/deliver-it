package com.alevel.stateMachine.com.alevel.premise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parcel {
    public Parcel(String name,
                  Boolean isRegistered,
                  Boolean isChecked,
                  Boolean isSorting,
                  Boolean isBilling,
                  Boolean isDamaged,
                  Boolean isStamped,
                  Boolean isSanded) {
        this.name = name;
        this.isRegistered = isRegistered;
        this.isChecked = isChecked;
        this.isSorting = isSorting;
        this.isBilling = isBilling;
        this.isDamaged = isDamaged;
        this.isStamped = isStamped;
        this.isSanded = isSanded;
    }

    public String name;
    public Boolean isRegistered;
    public Boolean isChecked;
    public Boolean isSorting;
    public Boolean isBilling;
    public Boolean isStamped;
    public Boolean isDamaged;
    public Boolean isSanded;

}
