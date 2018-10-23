package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;

public class CheckAddressCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isCheckedAddress = true;
    }
}
