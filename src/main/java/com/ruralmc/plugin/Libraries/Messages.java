package com.ruralmc.plugin.Libraries;

import org.bukkit.ChatColor;

public class Messages {

    //Private may cause errors when accessing from separate class
    public static final String CHAT_PREFIX = ChatColor.WHITE + "[" + ChatColor.AQUA + "Rural" + ChatColor.GOLD + "MC" + ChatColor.WHITE + "] " + ChatColor.GREEN;
    public static final String NO_PERMS = CHAT_PREFIX + ChatColor.RED + "You do not have permission to use that command.";
    public static final String TEST = CHAT_PREFIX + ChatColor.GREEN + "This is a test thing";
    public static final String REGION_CLAIMED = CHAT_PREFIX + ChatColor.GREEN + "You paid " + CfgValues.landClaimPrice + " for your new claim.";
}
