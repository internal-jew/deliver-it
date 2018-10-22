package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.Context;

public class NotifyCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isNotified = true;
    }
}
