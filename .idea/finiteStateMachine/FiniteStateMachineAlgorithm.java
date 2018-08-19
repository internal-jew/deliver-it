package com.alevel.stateMachine;

import com.alevel.stateMachine.com.alevel.premise.Parcel;

import java.util.Iterator;
import java.util.Set;

import static com.alevel.stateMachine.StateCommands.*;


public class FiniteStateMachineAlgorithm extends FiniteStateMachine {
    static void runCommand(State state, Parcel parcel) {
        Set<State> transitMap = getTransitMap(state);
        Iterator<State> iterator = transitMap.iterator();

        while (iterator.hasNext()) {
            State iterState = iterator.next();
            try {
                switch (iterState) {
                    case START:
                        executeStart();
                        return;
                    case CHECKING:
                        executeChecking(parcel);
                        return;
                    case BILLING:
                        executeBilling(parcel);
                        return;
                    case SENDING:
                        executeSending(parcel);
                        return;
                    case SORTING:
                        executeSorting(parcel);
                        return;
                    case REGISTER:
                        executeRegister(parcel);
                        return;
                    case STAMPING:
                        executeStamping(parcel);
                        return;
                    case KICKVERYHARD:
                        executeKickVeryHard(parcel);
                        return;
                }
            } catch (CommandIsNotExists commandIsNotExists) {
                continue;
            }
        }
    }
    /**
     * @return a Set of possible transfers for current state
     */
    static Set<State> getTransitMap(State currentState) {
        return RoteMap.roteMap.get(currentState);
    }
}


