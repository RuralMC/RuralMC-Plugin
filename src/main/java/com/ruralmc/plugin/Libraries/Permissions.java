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

    //TODO implement this, it'll make things easier
    public static Permission GROUP_NOMAD = new Permission("rmc.nomad");
    public static Permission GROUP_CITIZEN = new Permission("rmc.citizen");
    public static Permission GROUP_PIONEER = new Permission("rmc.pioneer");
    public static Permission GROUP_STAFF = new Permission("rmc.staff");
    public static Permission GROUP_ADMIN = new Permission("rmc.admin");
}
