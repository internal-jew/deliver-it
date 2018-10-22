package com.alevel.deliverit.fsm;

import com.alevel.deliverit.logistics.fsm.Command;
import com.alevel.deliverit.logistics.fsm.CommandFactory;

import java.util.Optional;

import static com.alevel.deliverit.fsm.State.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class LogisticCommandFactory extends CommandFactory<State, LogisticContext> {

    @Override
    public Optional<Command<LogisticContext>> getCommand(State currentState, LogisticContext context) {
        switch (currentState) {

            case START:
                break;
            case PROCESS:
                if (!context.isProcessed) {
                    return of(new ProcessCommand());
                }
                break;
            case NOTIFY:
                break;
            case CHECKADDRESS:
                break;
            case RADRIATIONCONTROL:
                break;
            case CHECKLEGALITY:
                break;
            case CHECKFORDANGEROUS:
                break;
            case SORTING:
                break;
            case SENDING:
                break;
            case DEPARTED:
                break;
        }
        if (!context.isProcessed) {
            return of(new ProcessCommand());
        }
        if (currentState == CHECKADDRESS && (!context.isCheckedAddress)) {
            return of(new CheckAddressCommand());
        }
        if (currentState == RADRIATIONCONTROL && (!context.isRadioControl)) {
            return of(new RadiationControlCommand());
        }
        if (currentState == CHECKLEGALITY && (!context.isCheckedLegality)) {
            return of(new CheckLegalityCommand());
        }
        if (currentState == CHECKFORDANGEROUS && (!context.isCheckedForDangerous)) {
            return of(new CheckForDangerousCommand());
        }
        if (currentState == SORTING && (!context.isSorted)) {
            return of(new SortingCommand());
        }
        if (currentState == NOTIFY && (!context.isNotified)) {
            return of(new NotifyCommand());
        }
        if (currentState == SENDING && (!context.isSent)) {
            return of(new SendCommand());
        }
        if (currentState == DEPARTED && (!context.isSent)) {
            return of(new DepartedCommand());
        }
        return empty();
    }
}
