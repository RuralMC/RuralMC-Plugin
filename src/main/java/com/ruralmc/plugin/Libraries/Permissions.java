package com.ruralmc.plugin.Libraries;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class Permissions {
    public static void init(PluginManager pm) {
        pm.addPermission(CMD_RMC);
        pm.addPermission(CMD_CLAIM);
    }

    public static Permission CMD_RMC = new Permission("rmc.cmd.rmc");
    public static Permission CMD_CLAIM = new Permission("rmc.cmd.claim");
    public static Permission CMD_POINTS = new Permission("rmc.cmd.points");
    public static Permission CMD_POINTS_ADMIN = new Permission("rmc.cmd.points.admin");
}
