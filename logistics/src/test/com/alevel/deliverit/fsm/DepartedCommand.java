package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.Context;

public class DepartedCommand implements Command {
    @Override
    public void perform(Context context) {
        context.isDeparted = true;
    }
}

