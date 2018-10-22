package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Context;

/**
 * @author Vadym Mitin
 */
public class LogisticContext extends Context {
    public boolean isProcessed;
    public boolean isCheckedAddress;
    public boolean isRadioControl;
    public boolean isCheckedLegality = true;
    public boolean isCheckedForDangerous;
    public boolean isSorted;
    public boolean isNotified;
    public boolean isSent;
    public boolean isDeparted;
}
