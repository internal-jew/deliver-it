package com.alevel.deliverit.logistics.fsm;

public enum State {
    START,
    PROCESS,
    NOTIFY,
    CHECKADDRESS,
    RADRIATIONCONTROL,
    CHECKLEGALITY,
    CHECKFORDANGEROUS,
    SORTING,
    SENDING,
    DEPARTED,

    //    START,
    TERMINAL,
    STAMPING,
    ACCEPTING,
    WEIGHTING,
    RADIATION_CONTROL,
    TRASH,
    //    DEPARTED,
    LOST;
}
