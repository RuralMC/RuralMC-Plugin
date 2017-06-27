package com.ruralmc.plugin.Command;

import com.ruralmc.plugin.Libraries.Messages;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.RuralMC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RMCCommand implements CommandExecutor {

    private final RuralMC plugin;

    public RMCCommand(RuralMC plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender src, Command cmd, String alias, String[] args) {

        int length = args.length;

        if (cmd.getName().equalsIgnoreCase("rmc")) {
            if (src.hasPermission(Permissions.CMD_RMC)) {
                src.sendMessage(Messages.TEST);
            } else {
                src.sendMessage(Messages.NO_PERMS);
            }
            return true;
        }
        return false;
    }
}

