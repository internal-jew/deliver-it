package com.alevel.deliverit.logistics.fsm.commands;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.LogisticContext;

/**
 * @author Vadym Mitin
 */
public class DepartedCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.reset();
        context.isDeparted = true;
    }
}

