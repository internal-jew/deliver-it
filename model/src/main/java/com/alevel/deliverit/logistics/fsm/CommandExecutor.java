package com.alevel.deliverit.logistics.fsm;
/**
 * @author Vitalii Usatyi
 */
public class CommandExecutor {
    public static void execute(Command command, Context context) {
        command.perform(context);
    }
}
