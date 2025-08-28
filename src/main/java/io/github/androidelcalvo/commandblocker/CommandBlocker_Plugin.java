package io.github.androidelcalvo.commandblocker;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class CommandBlocker_Plugin extends JavaPlugin implements Listener {

    private List<String> whitelistedPlayers;
    private List<String> blockedCommands;
    private String banCommand;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(this, this);

        whitelistedPlayers = this.getConfig().getStringList("Whitelist");
        blockedCommands = this.getConfig().getStringList("Commands");
        banCommand = this.getConfig().getString("CustomCommand", "/ban {player}");
    }

    @EventHandler
    private void commandProcess(PlayerCommandPreprocessEvent event) {
        String playerName = event.getPlayer().getName();

        if (whitelistedPlayers.contains(playerName)) {
            return;
        }

        String inputCommand = event.getMessage().split(" ")[0];

        for (String command : blockedCommands) {
            if (inputCommand.equalsIgnoreCase(command)) {
                event.setCancelled(true);

                String finalBanCommand = banCommand.replace("{player}", playerName);

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalBanCommand.substring(1));

                return;
            }
        }
    }
}
