package com.ruralmc.plugin;

import com.ruralmc.plugin.Command.*;
import com.ruralmc.plugin.Libraries.Config;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.Listeners.PlayerListener;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RuralMC extends JavaPlugin {

    private PluginManager pm = this.getServer().getPluginManager();
    private PluginDescriptionFile desc = getDescription();
    public RegionContainer container = getWorldGuard().getRegionContainer();

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        new Config(this);
        Config.createAllFiles();

        Permissions.init(this.pm);

        new PlayerListener(this);

        getWorldGuard();

        this.getCommand("rmc").setExecutor(new RMCCommand(this));
        this.getCommand("points").setExecutor(new PointsCommand(this));
        this.getCommand("addpoints").setExecutor(new AddPointsCommand(this));
        this.getCommand("removepoints").setExecutor(new RemovePointsCommand(this));
        this.getCommand("setpoints").setExecutor(new SetPointsCommand(this));
        this.getCommand("claim").setExecutor(new ClaimCommand(this));

        this.saveDefaultConfig();

        this.getLogger().log(Level.INFO, "Enabled RuralMC v" + desc.getVersion());
    }

    @Override
    public void onDisable() {

        Config.saveAllFiles();

        this.getLogger().log(Level.INFO, "Disabled RuralMC v" + desc.getVersion());
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }
}
