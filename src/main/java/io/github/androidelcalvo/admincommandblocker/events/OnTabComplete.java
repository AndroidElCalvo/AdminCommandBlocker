package io.github.androidelcalvo.admincommandblocker.events;

import io.github.androidelcalvo.admincommandblocker.Command;
import io.github.androidelcalvo.admincommandblocker.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnTabComplete implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
                                                @NotNull org.bukkit.command.Command command,
                                                @NotNull String alias,
                                                @NotNull String[] args) {

        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            for (Command cmd : CommandManager.getCommands()) {
                if (cmd.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(cmd.getName());
                }
            }
            return completions;
        }

        return Collections.emptyList();
    }
}