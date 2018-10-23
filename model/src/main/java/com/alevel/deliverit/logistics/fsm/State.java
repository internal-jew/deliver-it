package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
public enum State {
    START,
    EXPECTATION,
    STAMPING,
    ACCEPTING,
    WEIGHTING,
    RADIATION_CONTROL,
    TRASH,
    DEPARTED,
    LOST;
}
