package io.github.androidelcalvo.admincommandblocker;

import io.github.androidelcalvo.admincommandblocker.commands.ReloadCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private static final List<Command> Commands = new ArrayList<>();

    public static void init() {
        // Add New Commands
        register(new ReloadCommand());
    }

    public static void register(Command command) {
        Commands.add(command);
    }

    public static List<Command> getCommands() {
        return Commands;
    }

    public static Command getCommand(String name) {
        for (Command cmd : Commands) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }
        return null;
    }
}