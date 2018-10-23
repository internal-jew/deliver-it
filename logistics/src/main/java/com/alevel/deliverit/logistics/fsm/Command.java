package com.alevel.deliverit.logistics.fsm;

/**
 * @author Vitalii Usatyi
 */
public interface Command<C extends Context> {
    void perform(C context);
}
