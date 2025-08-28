package io.github.androidelcalvo.admincommandblocker.commands;

import io.github.androidelcalvo.admincommandblocker.AdminCommandBlocker_Plugin;
import io.github.androidelcalvo.admincommandblocker.Command;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super("reload", "Reloads the plugin config");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("admincommandblocker.reload")) {
            sender.sendMessage("§cYou don't have permission to do that.");
            return;
        }

        AdminCommandBlocker_Plugin.getInstance().reload();
        sender.sendMessage("§aAdminCommandBlocker config reloaded!");
    }
}