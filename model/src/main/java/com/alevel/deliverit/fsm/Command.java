package com.alevel.deliverit.fsm;

/**
 * @author Vitalii Usatyi
 */
interface Command {
    void perform(Context context);
}
