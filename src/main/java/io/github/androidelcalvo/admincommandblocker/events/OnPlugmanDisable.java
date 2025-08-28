package io.github.androidelcalvo.admincommandblocker.events;

import io.github.androidelcalvo.admincommandblocker.AdminCommandBlocker_Plugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class OnPlugmanDisable implements Listener {

    private final AdminCommandBlocker_Plugin plugin;

    public OnPlugmanDisable(AdminCommandBlocker_Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        String command = event.getCommand().toLowerCase();
        String pluginName = plugin.getDescription().getName().toLowerCase();

        if ((command.contains("plugman disable") && command.contains(pluginName)) ||
                (command.contains("plugman reload") && command.contains(pluginName)) ||
                (command.contains("plugman unload") && command.contains(pluginName)) ||
                (command.contains("plugman restart") && command.contains(pluginName))) {

            event.setCancelled(true);
            Bukkit.getConsoleSender().sendMessage("§4" + plugin.getDescription().getName() + " cannot be deactivated!");
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase();
        String pluginName = plugin.getDescription().getName().toLowerCase();

        if ((message.startsWith("/plugman disable") && message.contains(pluginName)) ||
                (message.startsWith("/plugman reload") && message.contains(pluginName)) ||
                (message.startsWith("/plugman unload") && message.contains(pluginName)) ||
                (message.startsWith("/plugman restart") && message.contains(pluginName))) {

            event.setCancelled(true);
            event.getPlayer().sendMessage("§4" + plugin.getDescription().getName() + " cannot be deactivated!");
        }
    }
}
