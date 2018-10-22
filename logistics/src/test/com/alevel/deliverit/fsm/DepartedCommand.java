package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.Context;

public class DepartedCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isDeparted = true;
    }
}

