package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.Context;

public class NotifyCommand implements Command {
    @Override
    public void perform(Context context) {
        context.isNotified = true;
    }
}
