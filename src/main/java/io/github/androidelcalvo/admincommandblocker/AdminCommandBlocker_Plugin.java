package io.github.androidelcalvo.admincommandblocker;

import io.github.androidelcalvo.admincommandblocker.events.OnPlugmanDisable;
import io.github.androidelcalvo.admincommandblocker.events.OnTabComplete;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Objects;

public final class AdminCommandBlocker_Plugin extends JavaPlugin implements Listener {

    private List<String> whitelistedPlayers;
    private List<String> blockedCommands;
    private String customCommand;

    private static AdminCommandBlocker_Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        ensureConfigExists();
        loadValues();

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new OnPlugmanDisable(this), this);

        //noinspection DataFlowIssue
        this.getCommand("acb").setExecutor((sender, command, label, args) -> {
            if (args.length == 0) {
                sender.sendMessage("§eUsage: /acb <subcommand>");
                return true;
            }

            Command sub = CommandManager.getCommand(args[0]);
            if (sub == null) {
                sender.sendMessage("§cUnknown command.");
                return true;
            }

            sub.execute(sender, args);
            return true;
        });

        Objects.requireNonNull(this.getCommand("acb")).setTabCompleter(new OnTabComplete());

        CommandManager.init();
    }

    @EventHandler
    public void commandProcess(PlayerCommandPreprocessEvent event) {
        String playerName = event.getPlayer().getName();

        if (whitelistedPlayers.contains(playerName)) return;

        String inputCommand = event.getMessage().split(" ")[0];

        for (String command : blockedCommands) {
            if (inputCommand.equalsIgnoreCase(command)) {
                event.setCancelled(true);

                String finalCustomCommand = customCommand.replace("{player}", playerName);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCustomCommand);

                return;
            }
        }
    }

    public void reload() {
        ensureConfigExists();
        this.reloadConfig();
        loadValues();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void ensureConfigExists() {
        if (!getDataFolder().exists()) getDataFolder().mkdirs();
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
    }

    private void loadValues() {
        whitelistedPlayers = this.getConfig().getStringList("Whitelist");
        blockedCommands = this.getConfig().getStringList("Commands");
        customCommand = this.getConfig().getString("CustomCommand", "kick {player} Using blocked commands");
    }

    @SuppressWarnings("unused")
    public List<String> getWhitelistedPlayers() {
        return whitelistedPlayers;
    }

    public static AdminCommandBlocker_Plugin getInstance() {
        return instance;
    }
}