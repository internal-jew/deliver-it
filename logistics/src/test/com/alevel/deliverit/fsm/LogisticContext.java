package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Context;

public class LogisticContext extends Context {
    boolean isProcessed;
    boolean isCheckedAddress;
    boolean isRadioControl;
    boolean isCheckedLegality = true;
    boolean isCheckedForDangerous;
    boolean isSorted;
    boolean isNotified;
    boolean isSanded;
    boolean isDeparted;
}
