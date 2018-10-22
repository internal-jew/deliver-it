package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.Context;

public class ProcessCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isProcessed = true;
    }
}
