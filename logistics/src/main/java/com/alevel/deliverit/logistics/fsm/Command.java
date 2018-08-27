package com.alevel.deliverit.logistics.fsm;

interface Command {
    void perform(Context context);
}
