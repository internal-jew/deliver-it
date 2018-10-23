package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;

public class SortingCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isSorted = true;
    }
}
