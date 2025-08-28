package io.github.androidelcalvo.admincommandblocker;

import org.bukkit.command.CommandSender;

public abstract class Command {

    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }

    public abstract void execute(CommandSender sender, String[] args);
}