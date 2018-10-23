package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vadym Mitin
 */
public class LogisticContext extends Context {

    public boolean isExpectation = true;
    public boolean isAccepting;
    public boolean isWeighting;
    public boolean isRadiationControl;
    public boolean isStamping;
    public boolean isDeparted;
//    public boolean isTrash;
//    public boolean isLost;

    public void reset() {
        isExpectation = false;
        isAccepting = false;
        isWeighting = false;
        isRadiationControl = false;
        isStamping = false;
        isDeparted = false;
//        isTrash = false;
//        isLost = false;
    }
}
