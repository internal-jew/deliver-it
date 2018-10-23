package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;

public class CheckForDangerousCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isCheckedForDangerous = true;
    }
}
