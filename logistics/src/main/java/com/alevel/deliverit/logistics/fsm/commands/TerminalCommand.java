package com.alevel.deliverit.logistics.fsm.commands;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.LogisticContext;

/**
 * @author Vadym Mitin
 */
public class TerminalCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.reset();
        context.isTerminal = true;
    }
}
