package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
interface Command {
    void perform(Context context);
}
