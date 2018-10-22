package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.Context;

public class CheckLegalityCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isCheckedLegality = true;
    }
}
