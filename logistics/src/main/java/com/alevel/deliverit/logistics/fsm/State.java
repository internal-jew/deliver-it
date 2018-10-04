package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
public enum State {
    START,
    REGISTER,
    CHECKING,
    SORTING,
    BILLING,
    STAMPING,
    KICKVERYHARD,
    SENDING,
    TERMINAL,
    LOST,
    DEPARTED
}
