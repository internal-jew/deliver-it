package com.alevel.deliverit.logistics.fsm;

import static com.alevel.deliverit.logistics.fsm.State.*;

/**
 * @author Vadym Mitin
 */
public class StateMachineImpl extends StateMachine {
    private State currentState;

    public StateMachineImpl() {
        this.currentState = TERMINAL;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void restart() {
        this.currentState = TERMINAL;
    }

    @Override
    void switchState(State nextState) {
        currentState = nextState;
    }

//    ***Possible states***
//
//    TERMINAL -> ACCEPTING -> WEIGHING -> RADIATION_CONTROL -> STAMPING -> DEPARTED
//                  \        /                                                  \
//                   \     /                                                     \
//                    TRASH                                                      LOST
//

    private void stateTerminal() {
        switchState(ACCEPTING);
    }

    private void stateAccepting() {
        switchState(WEIGHTING);
    }

    private void stateWeighting() {
        switchState(RADIATION_CONTROL);
    }

    private void stateRadiationControl() {
        switchState(STAMPING);
    }

    private void stateStamping() {
        switchState(DEPARTED);
    }

    private void stateDeparted() {

    }

    private void stateTrash() {
        // exit
    }

    private void stateLost() {
        // exit
    }

    @Override
    public void handle() {
        switch (currentState) {
            case LOST:
                System.out.println("State lost");
                stateLost();
                break;
            case START:
                System.out.println("State start");
                break;
            case TRASH:
                System.out.println("state trash");
                stateTrash();
                break;
            case DEPARTED:
                System.out.println("state departed");
                stateDeparted();
                break;
            case STAMPING:
                System.out.println("state stamping");
                stateStamping();
                break;
            case TERMINAL:
                System.out.println("state terminal");
                stateTerminal();
                break;
            case ACCEPTING:
                System.out.println("state accepting");
                stateAccepting();
                break;
            case WEIGHTING:
                System.out.println("state weighting");
                stateWeighting();
                break;
            case RADIATION_CONTROL:
                System.out.println("state radiation control");
                stateRadiationControl();
                break;
        }
    }
}
