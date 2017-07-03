package com.ruralmc.plugin.Command;

import com.ruralmc.plugin.Libraries.Config;
import com.ruralmc.plugin.Libraries.Messages;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.RuralMC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetPointsCommand implements CommandExecutor {

    private final RuralMC plugin;

    public SetPointsCommand(RuralMC plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender src, Command cmd, String alias, String[] args) {

        int length = args.length;

        if (cmd.getName().equalsIgnoreCase("setpoints")) {
            if (!(length < 2 || length > 2)) {
                if (src.hasPermission(Permissions.GROUP_STAFF)) {
                    Player player = this.plugin.getServer().getPlayer(UUID.fromString(args[0]));

                    Config.getPoints().set(player.getUniqueId() + ".points", args[1]);
                    Config.savePointsFile();
                    src.sendMessage(Messages.CHAT_PREFIX + "User's points set to: " + ChatColor.GOLD + args[1]);
                    player.sendMessage(Messages.CHAT_PREFIX + ChatColor.GREEN + " Your points have been set to: " + ChatColor.GOLD + args[1]);
                }
            } else {
                src.sendMessage(Messages.INVALID_SYNTAX);
            }
        }
        return false;
    }
}
