package com.ruralmc.plugin;

import com.ruralmc.plugin.Command.ClaimCommand;
import com.ruralmc.plugin.Command.RMCCommand;
import com.ruralmc.plugin.Libraries.Config;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.Libraries.SQL.MySQL;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class RuralMC extends JavaPlugin {

    private PluginManager pm = this.getServer().getPluginManager();
    MySQL sql = new MySQL("host.name", "port", "database", "user", "pass");
    Connection con = null;

    @Override
    public void onEnable() {

        new Config(this);
        Config.createAllFiles();

        Permissions.init(this.pm);

        try {
            con = sql.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            this.getLogger().log(Level.SEVERE, "ERROR: SQLException - RuralMC.onEnable.openConnection");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this.getLogger().log(Level.SEVERE, "ERROR: ClassNotFoundException - RuralMC.onEnable.openConnection");
        }

        this.getCommand("rmc").setExecutor(new RMCCommand(this));
        this.getCommand("claim").setExecutor(new ClaimCommand(this));

        this.saveDefaultConfig();

        this.getLogger().log(Level.INFO, "Enabled");
    }

    @Override
    public void onDisable() {

        Config.saveAllFiles();

        this.getLogger().log(Level.INFO, "Disabled");
    }
}
