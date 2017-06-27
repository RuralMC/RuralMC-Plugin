package com.ruralmc.plugin;

import com.ruralmc.plugin.Command.ClaimCommand;
import com.ruralmc.plugin.Command.RMCCommand;
import com.ruralmc.plugin.Libraries.Permissions;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RuralMC extends JavaPlugin {

    private PluginManager pm = this.getServer().getPluginManager();

    @Override
    public void onEnable() {

        Permissions.init(this.pm);

        this.getCommand("rmc").setExecutor(new RMCCommand(this));
        this.getCommand("claim").setExecutor(new ClaimCommand(this));

        this.saveDefaultConfig();

        this.getLogger().log(Level.INFO, "Enabled");
    }

    @Override
    public void onDisable() {

        this.getLogger().log(Level.INFO, "Disabled");
    }
}
