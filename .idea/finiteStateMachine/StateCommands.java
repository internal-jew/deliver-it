package com.alevel.stateMachine;

import com.alevel.stateMachine.com.alevel.premise.Parcel;

import static com.alevel.stateMachine.State.*;

public class StateCommands {
    static void executeStart() {
        System.out.println("Machine started");
        FiniteStateMachine.currentState = START;
    }

    static void executeRegister(Parcel parcel) throws CommandIsNotExists {
        if (parcel.isRegistered) {
            throw new CommandIsNotExists();
        }
        System.out.println("Parcel is registering");
        parcel.isRegistered = true;
        FiniteStateMachine.currentState = REGISTER;
    }

    static void executeChecking(Parcel parcel) throws CommandIsNotExists {
        if (parcel.isChecked) {
            throw new CommandIsNotExists();
        }
        System.out.println("Parcel is caking");
        parcel.isChecked = true;
        FiniteStateMachine.currentState = CHECKING;
    }

    static void executeSorting(Parcel parcel) throws CommandIsNotExists {
        if (parcel.isSorting) {
            throw new CommandIsNotExists();
        }
        System.out.println("Parcel is sorting");
        parcel.isSorting = true;
        FiniteStateMachine.currentState = SORTING;
    }

    static void executeBilling(Parcel parcel) throws CommandIsNotExists {
        if (parcel.isBilling) {
            throw new CommandIsNotExists();
        }
        System.out.println("Parcel is billing");
        parcel.isBilling = true;
        FiniteStateMachine.currentState = BILLING;
    }

    static void executeStamping(Parcel parcel) throws CommandIsNotExists {
        if (parcel.isStamped) {
            throw new CommandIsNotExists();
        }
        System.out.println("Parcel is stamping");
        parcel.isStamped = true;
        FiniteStateMachine.currentState = STAMPING;
    }

    static void executeKickVeryHard(Parcel parcel) throws CommandIsNotExists {
        if (parcel.isDamaged) {
            throw new CommandIsNotExists();
        }
        System.out.println("kicking the parcel as hard as can");
        parcel.isDamaged = true;
        FiniteStateMachine.currentState = KICKVERYHARD;
    }

    static void executeSending(Parcel parcel) throws CommandIsNotExists {
        if (parcel.isSanded) {
            throw new CommandIsNotExists();
        }
        System.out.println("Parcel is sending");
        parcel.isSanded = true;
        FiniteStateMachine.currentState = TERMINAL;
    }
}
