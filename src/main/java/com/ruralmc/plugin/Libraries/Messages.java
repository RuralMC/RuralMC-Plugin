package com.ruralmc.plugin.Libraries;

import org.bukkit.ChatColor;

public class Messages {

    //Private may cause errors when accessing from separate class
    public static final String CHAT_PREFIX = ChatColor.WHITE + "[" + ChatColor.AQUA + "Rural" + ChatColor.GOLD + "MC" + ChatColor.WHITE + "] " + ChatColor.GREEN;
    public static final String NO_PERMS = CHAT_PREFIX + ChatColor.RED + "You do not have permission to use that command.";
    public static final String TEST = CHAT_PREFIX + "This is a test thing";
    public static final String REGION_CLAIMED = CHAT_PREFIX + "You paid " + ChatColor.GOLD + CfgValues.landClaimPrice + ChatColor.GREEN + " point for your new claim.";
    public static final String INVALID_SYNTAX = CHAT_PREFIX + ChatColor.RED + "Invalid command syntax.";

    public static String setPlayerPoints(String username, int points) {
        return CHAT_PREFIX + "Successfully set " + ChatColor.AQUA + username + "'s " + ChatColor.GREEN + "points to: " + ChatColor.GOLD + points + ChatColor.GREEN + " points";
    }

    public static String getPlayerPoints(String username, int points) {
        return CHAT_PREFIX + "User: " + ChatColor.AQUA + ChatColor.GREEN + "has: " + ChatColor.GOLD + points + ChatColor.GREEN + " points";
    }
}
