package com.ruralmc.plugin.Listeners;

import com.ruralmc.plugin.Libraries.Config;
import com.ruralmc.plugin.RuralMC;
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

        if (!(player.hasPlayedBefore())) {
            Config.getClaims().createSection(player.getUniqueId() + ".claims");
            Config.getClaims().createSection(player.getUniqueId() + ".name");
            Config.getClaims().set(player.getUniqueId() + ".name", player.getName());
            this.plugin.getServer().getLogger().log(Level.INFO, "New player detected, creating claims section for player: " + player.getName());
            Config.saveClaimsFile();
        }
    }
}
