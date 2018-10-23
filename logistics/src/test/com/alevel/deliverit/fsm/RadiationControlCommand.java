package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;

public class RadiationControlCommand implements Command<LogisticContext> {
    @Override
    public void perform(LogisticContext context) {
        context.isRadioControl = true;
    }
}
