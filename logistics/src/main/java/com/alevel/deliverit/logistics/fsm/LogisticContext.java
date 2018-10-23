package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vadym Mitin
 */
public class LogisticContext extends Context {

    public boolean isTerminal;
    public boolean isAccepting;
    public boolean isWeighting;
    public boolean isRadiationControl;
    public boolean isStamping;
    public boolean isDeparted;
    public boolean isTrash;
    public boolean isLost;
}
