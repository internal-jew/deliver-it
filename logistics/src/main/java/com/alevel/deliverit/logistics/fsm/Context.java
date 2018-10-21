package com.alevel.deliverit.logistics.fsm;


import com.google.common.annotations.VisibleForTesting;

/**
 * @author Vitalii Usatyi
 */
@VisibleForTesting
  public class Context {
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
