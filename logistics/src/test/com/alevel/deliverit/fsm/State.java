package com.alevel.deliverit.fsm;

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
    DEPARTED
}
