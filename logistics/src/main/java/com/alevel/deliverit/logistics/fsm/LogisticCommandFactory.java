package com.alevel.deliverit.logistics.fsm;

import com.alevel.deliverit.logistics.fsm.commands.*;

import java.util.Optional;

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
                if (!context.isNotified) {
                    return of(new NotifyCommand());
                }
                break;
            case CHECKADDRESS:
                if (!context.isCheckedAddress) {
                    return of(new CheckAddressCommand());
                }
                break;
            case RADRIATIONCONTROL:
                if (!context.isRadioControl) {
                    return of(new RadiationControlCommand());
                }
                break;
            case CHECKLEGALITY:
                if (!context.isCheckedLegality) {
                    return of(new CheckLegalityCommand());
                }
                break;
            case CHECKFORDANGEROUS:
                if (!context.isCheckedForDangerous) {
                    return of(new CheckForDangerousCommand());
                }
                break;
            case SORTING:
                if (!context.isSorted) {
                    return of(new SortingCommand());
                }
                break;
            case SENDING:
                if (!context.isSent) {
                    return of(new SendCommand());
                }
                break;
            case DEPARTED:
                if (!context.isSent) {
                    return of(new DepartedCommand());
                }
                break;
        }
        return empty();
    }
}
