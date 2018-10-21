package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
public  class Context {
    public  boolean isProcessed;
    public  boolean isCheckedAddress;
    public  boolean isRadioControl;
    public  boolean isCheckedLegality = true;
    public  boolean isCheckedForDangerous;
    public  boolean isSorted;
    public  boolean isNotified;
    public  boolean isSanded;
    public  boolean isDeparted;
}
