package com.ruralmc.plugin.Command;

import com.ruralmc.plugin.Libraries.Messages;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.RuralMC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PointsCommand implements CommandExecutor {

    private final RuralMC plugin;

    public PointsCommand(RuralMC plugin) {
        this.plugin = plugin;
    }


    public boolean onCommand(CommandSender src, Command cmd, String alias, String[] args) {

        int length = args.length;

        if (cmd.getName().equalsIgnoreCase("points")) {
            if (src.hasPermission(Permissions.CMD_POINTS)) {
                if (length == 0) {
                    //Players number of points
                    src.sendMessage(Messages.CHAT_PREFIX + "You currently have: " + ChatColor.GOLD + "{$POINTS} " + ChatColor.GREEN + "Points");
                }
            } else {
                src.sendMessage(Messages.NO_PERMS);
            }
            return true;
        }
        return false;
    }
}

