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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

public class ClaimCommand implements CommandExecutor {

    private final RuralMC plugin;

    public ClaimCommand(RuralMC plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender src, Command cmd, String alias, String[] args) {

        int length = args.length;

        if (cmd.getName().equalsIgnoreCase("claim")) {
            if (!(src instanceof Player)) {
                return true;
            }
            if (src.hasPermission(Permissions.CMD_CLAIM)) {
                Player player = (Player) src;
                UUID uuid = UUID.randomUUID();

                if (Config.getPoints().getInt(player.getUniqueId() + ".points") > 1) {
                    player.performCommand("/chunk");
                    player.performCommand("rg claim " + uuid.toString());
                /*String chunk = player.getLocation().getChunk().toString();
                player.sendMessage(chunk);*/

                    List<String> claims = Config.getClaims().getStringList(player.getUniqueId() + ".claims");
                    claims.add(uuid.toString());
                    Config.getClaims().set(player.getUniqueId() + ".claims", claims);

                    int numclaims = Config.getClaims().getInt(player.getUniqueId() + ".number");
                    numclaims++;
                    Config.getClaims().set(player.getUniqueId() + ".number", numclaims);

                    Config.saveClaimsFile();

                    int points = Config.getPoints().getInt(player.getUniqueId() + ".points");
                    points--;
                    Config.getPoints().set(player.getUniqueId() + ".points", points);
                    Config.savePointsFile();

                    player.sendMessage(Messages.REGION_CLAIMED);
                    return true;
                } else {
                    src.sendMessage(Messages.CHAT_PREFIX + ChatColor.RED + "You do not have enough points to claim this chunk.");
                    return true;
                }
            } else {
                src.sendMessage(Messages.NO_PERMS);
                return true;
            }
        }
        return false;
    }
}