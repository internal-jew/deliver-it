package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
public enum State {
    START,
    STANDBY,
    STAMPING,
    ACCEPTING,
    WEIGHTING,
    RADIATION_CONTROL,
    TRASH,
    DEPARTED,
    LOST;
}
