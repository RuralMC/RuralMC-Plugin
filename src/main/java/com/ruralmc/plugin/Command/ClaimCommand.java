package com.ruralmc.plugin.Command;

import com.ruralmc.plugin.Libraries.CfgValues;
import com.ruralmc.plugin.Libraries.Config;
import com.ruralmc.plugin.Libraries.Messages;
import com.ruralmc.plugin.Libraries.Permissions;
import com.ruralmc.plugin.RuralMC;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.RegionGroup;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
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
                final Player player = (Player) src;
                final UUID uuid = UUID.randomUUID();

                if (Config.getPoints().getInt(player.getUniqueId() + ".points") > 0) {

                    //START REGIONS
                    RegionManager regions = plugin.container.get(player.getWorld());

                    Chunk chunk = player.getLocation().getChunk();

                    int cx = chunk.getX();
                    int cz = chunk.getZ();

                    int minX = cx * 16;
                    int minZ = cz * 16;

                    int maxX = minX + 15;
                    int maxZ = minZ + 15;

                    BlockVector min = new BlockVector(minX, 33, minZ);
                    BlockVector max = new BlockVector(maxX, 129, maxZ);

                    ProtectedRegion region = new ProtectedCuboidRegion(uuid.toString(), min, max);

                    if (regions != null) {
                        regions.addRegion(region);
                    }

                    region.setFlag(DefaultFlag.GREET_MESSAGE, "&aEntering " + player.getName() + "'s claim.");
                    region.setFlag(DefaultFlag.FAREWELL_MESSAGE, "&cLeaving " + player.getName() + "'s claim.");
                    region.setFlag(DefaultFlag.BUILD, StateFlag.State.DENY);
                    region.setFlag(DefaultFlag.BUILD.getRegionGroupFlag(), RegionGroup.NON_MEMBERS);

                    DefaultDomain owners = region.getOwners();
                    owners.addPlayer(player.getUniqueId());
                    region.setOwners(owners);
                    //END REGIONS

                    //START CLAIMS
                    List<String> claims = Config.getClaims().getStringList(player.getUniqueId() + ".claims");
                    claims.add(uuid.toString());
                    Config.getClaims().set(player.getUniqueId() + ".claims", claims);

                    int numclaims = Config.getClaims().getInt(player.getUniqueId() + ".number");
                    numclaims++;
                    Config.getClaims().set(player.getUniqueId() + ".number", numclaims);

                    Config.saveClaimsFile();
                    //END CLAIMS

                    //START POINTS
                    int points = Config.getPoints().getInt(player.getUniqueId() + ".points");
                    int price = CfgValues.landClaimPrice;
                    points = points - price;
                    Config.getPoints().set(player.getUniqueId() + ".points", points);

                    Config.savePointsFile();
                    //END POINTS

                    player.sendMessage(Messages.REGION_CLAIMED);
                    this.plugin.getServer().getConsoleSender().sendMessage(Messages.CHAT_PREFIX + ChatColor.GOLD + player.getName() + ChatColor.GREEN + " created a new claim: " + ChatColor.GOLD + uuid + ChatColor.GREEN + " at: " + ChatColor.AQUA + minX + ", " + minZ + " : " + maxX + ", " + maxZ);
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