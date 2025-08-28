package io.github.androidelcalvo.admincommandblocker;

import io.github.androidelcalvo.admincommandblocker.commands.ReloadCommand;
import io.github.androidelcalvo.admincommandblocker.events.OnPlugmanDisable;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class AdminCommandBlocker_Plugin extends JavaPlugin implements Listener {

    private List<String> whitelistedPlayers;
    private List<String> blockedCommands;
    private String banCommand;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        whitelistedPlayers = this.getConfig().getStringList("Whitelist");
        blockedCommands = this.getConfig().getStringList("Commands");
        banCommand = this.getConfig().getString("CustomCommand", "/ban {player} Using blocked commands");

        this.getServer().getPluginManager().registerEvents(this, this);

        //noinspection DataFlowIssue
        this.getCommand("acb").setExecutor(new ReloadCommand(this));

        this.getServer().getPluginManager().registerEvents(new OnPlugmanDisable(this), this);

    }

    @EventHandler
    public void commandProcess(PlayerCommandPreprocessEvent event) {
        String playerName = event.getPlayer().getName();

        if (whitelistedPlayers.contains(playerName)) return;

        String inputCommand = event.getMessage().split(" ")[0];

        for (String command : blockedCommands) {
            if (inputCommand.equalsIgnoreCase(command)) {
                event.setCancelled(true);

                String finalBanCommand = banCommand.replace("{player}", playerName);
                if (finalBanCommand.startsWith("/")) {
                    finalBanCommand = finalBanCommand.substring(1);
                }
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalBanCommand);

                return;
            }
        }
    }

    public void reloadAll() {
        this.reloadConfig();
        whitelistedPlayers = this.getConfig().getStringList("Whitelist");
        blockedCommands = this.getConfig().getStringList("Commands");
        banCommand = this.getConfig().getString("CustomCommand", "/ban {player} Using blocked commands");
    }

    @SuppressWarnings("unused")
    public List<String> getWhitelistedPlayers() {
        return whitelistedPlayers;
    }
}
