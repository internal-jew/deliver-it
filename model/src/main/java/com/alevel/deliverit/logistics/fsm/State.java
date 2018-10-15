package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
public enum State {
    START,
    STAMPING,
    TERMINAL,
    LOST,
    ACCEPTING, WEIGHTING, RADIATION_CONTROL, TRASH, DEPARTED
}
