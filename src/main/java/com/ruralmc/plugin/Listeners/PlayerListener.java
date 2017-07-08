package com.ruralmc.plugin.Listeners;

import com.ruralmc.plugin.Libraries.CfgValues;
import com.ruralmc.plugin.Libraries.Config;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.RuralMC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class PlayerListener implements Listener {

    private final RuralMC plugin;

    public PlayerListener(RuralMC plugin) {
        this.plugin = plugin;

        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        String name = player.getName();

        if (!(player.hasPlayedBefore())) {

            this.plugin.getServer().getLogger().log(Level.INFO, "New player detected, creating claims section for player: " + name);
            Config.getClaims().createSection(uuid + ".claims");
            Config.getClaims().createSection(uuid + ".name");
            Config.getClaims().set(uuid + ".name", name);
            Config.getClaims().createSection(uuid + ".number");
            Config.getClaims().set(uuid + ".number", 0);
            Config.saveClaimsFile();

            this.plugin.getServer().getLogger().log(Level.INFO, "New player detected, creating points section for player: " + name);
            Config.getPoints().createSection(uuid + ".points");
            Config.getPoints().set(uuid + ".points", CfgValues.defaultPoints);
            Config.getPoints().createSection(uuid + ".name");
            Config.getPoints().set(uuid + ".name", name);
            Config.savePointsFile();
        }

        if (player.hasPermission(Permissions.GROUP_NOMAD)) {
            player.setPlayerListName(ChatColor.YELLOW + player.getPlayerListName());
        }
        if (player.hasPermission(Permissions.GROUP_CITIZEN)) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getPlayerListName());
        }
        if (player.hasPermission(Permissions.GROUP_PIONEER)) {
            player.setPlayerListName(ChatColor.DARK_GREEN + player.getPlayerListName());
        }
        if (player.hasPermission(Permissions.GROUP_STAFF)) {
            player.setPlayerListName(ChatColor.DARK_AQUA + player.getPlayerListName());
        }
        if (player.hasPermission(Permissions.GROUP_ADMIN)) {
            player.setPlayerListName(ChatColor.DARK_RED + player.getPlayerListName());
        }
    }
}
