package com.ruralmc.plugin.Libraries;

import com.ruralmc.plugin.RuralMC;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Config {

    private static RuralMC plugin;

    private static File dataFolder;

    private static File configFile;
    private static File claimsFile;

    private static FileConfiguration config;
    private static FileConfiguration claims;

    public Config(RuralMC plugin) {
        this.plugin = plugin;

        this.dataFolder = plugin.getDataFolder();

        this.configFile = new File(plugin.getDataFolder(), "config.yml");
        this.claimsFile = new File(plugin.getDataFolder(), "claims.yml");

        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.claims = YamlConfiguration.loadConfiguration(claimsFile);
    }

    public static void createAllFiles() {
        if (!(configFile.exists())) {
            plugin.getLogger().log(Level.INFO, "config.yml not found, Creating new one.");
            plugin.saveResource("config.yml", true);
            plugin.getLogger().log(Level.INFO, "Successfully created config.yml");
        }
        if (!(claimsFile.exists())) {
            plugin.getLogger().log(Level.INFO, "claims.yml not found, Creating new one.");
            plugin.saveResource("claims.yml", true);
            plugin.getLogger().log(Level.INFO, "Successfully created claims.yml");
        }
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static File getClaimsFile() {
        return claimsFile;
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getClaims() {
        return claims;
    }

    public static FileConfiguration getFileConfig(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveConfigFile() {
        saveFile(configFile, config);
    }

    public static void saveClaimsFile() {
        saveFile(claimsFile, claims);
    }

    public static void saveAllFiles() {
        saveFile(configFile, config);
        saveFile(claimsFile, claims);
    }

    public static void saveFile(File file, FileConfiguration config) {
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
