package io.github.androidelcalvo.admincommandblocker.commands;

import io.github.androidelcalvo.admincommandblocker.AdminCommandBlocker_Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final AdminCommandBlocker_Plugin plugin;

    public ReloadCommand(AdminCommandBlocker_Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§eUsage: /acb reload");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("admincommandblocker.reload")) {
                sender.sendMessage("§cYou do not have permission to do that.");
                return true;
            }

            plugin.reloadAll();

            sender.sendMessage("§aAdminCommandBlocker configuration reloaded!");
            return true;
        }

        sender.sendMessage("§cUnknown subcommand. Usage: /acb reload");
        return true;
    }
}
