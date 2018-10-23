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
            case TERMINAL:
                if (!context.isTerminal) {
                    return of(new TerminalCommand());
                }
                break;
            case ACCEPTING:
                if (!context.isAccepting) {
                    return of(new AcceptingCommand());
                }
                break;
            case WEIGHTING:
                if (!context.isWeighting) {
                    return of(new WeightingCommand());
                }
                break;
            case RADIATION_CONTROL:
                if (!context.isRadiationControl) {
                    return of(new RadiationControlCommand());
                }
                break;
            case STAMPING:
                if (!context.isStamping) {
                    return of(new StampingCommand());
                }
                break;
            case TRASH:
                if (!context.isTrash) {
                    return of(new TrashCommand());
                }
                break;
            case LOST:
                if (!context.isLost) {
                    return of(new LostCommand());
                }
                break;
            case DEPARTED:
                if (!context.isDeparted) {
                    return of(new DepartedCommand());
                }
                break;
        }
        return empty();
    }
}
