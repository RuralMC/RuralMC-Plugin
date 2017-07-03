package com.ruralmc.plugin;

import com.ruralmc.plugin.Command.*;
import com.ruralmc.plugin.Libraries.Config;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.Listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RuralMC extends JavaPlugin {

    private PluginManager pm = this.getServer().getPluginManager();

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        new Config(this);
        Config.createAllFiles();

        Permissions.init(this.pm);

        new PlayerListener(this);

        this.getCommand("rmc").setExecutor(new RMCCommand(this));
        this.getCommand("claim").setExecutor(new ClaimCommand(this));
        this.getCommand("points").setExecutor(new PointsCommand(this));
        this.getCommand("addpoints").setExecutor(new AddPointsCommand(this));
        this.getCommand("removepoints").setExecutor(new RemovePointsCommand(this));
        this.getCommand("setpoints").setExecutor(new SetPointsCommand(this));

        this.saveDefaultConfig();

        this.getLogger().log(Level.INFO, "Enabled");
    }

    @Override
    public void onDisable() {

        Config.saveAllFiles();

        this.getLogger().log(Level.INFO, "Disabled");
    }
}
