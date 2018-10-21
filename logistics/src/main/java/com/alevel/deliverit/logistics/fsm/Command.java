package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
public interface Command {
    void perform(Context context);
}
