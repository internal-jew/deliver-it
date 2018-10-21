package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.CommandFactory;
import com.alevel.deliverit.logistics.fsm.Context;
import com.google.common.base.Optional;

import static com.alevel.deliverit.fsm.State.*;

public class LogisticCommandFactory extends CommandFactory<State> {

    @Override
    public Optional<Command> getCommand(State currentState, Context context) {

        if (currentState == PROCESS && (!context.isProcessed)) {
            return Optional.of(new ProcessCommand());
        }
        if (currentState == CHECKADDRESS && (!context.isCheckedAddress)) {
            return Optional.of(new CheckAddressCommand());
        }
        if (currentState == RADRIATIONCONTROL && (!context.isRadioControl)) {
            return Optional.of(new RadiationControlCommand());
        }
        if (currentState == CHECKLEGALITY && (!context.isCheckedLegality)) {
            return Optional.of(new CheckLegalityCommand());
        }
        if (currentState == CHECKFORDANGEROUS && (!context.isCheckedForDangerous)) {
            return Optional.of(new CheckForDangerousCommand());
        }
        if (currentState == SORTING && (!context.isSorted)) {
            return Optional.of(new SortingCommand());
        }
        if (currentState == NOTIFY && (!context.isNotified)) {
            return Optional.of(new NotifyCommand());
        }
        if (currentState == SENDING && (!context.isSanded)) {
            return Optional.of(new SendCommand());
        }
        if (currentState == DEPARTED && (!context.isSanded)) {
            return Optional.of(new DepartedCommand());
        }
        return Optional.absent();
    }
}
