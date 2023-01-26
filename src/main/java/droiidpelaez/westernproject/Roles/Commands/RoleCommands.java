package droiidpelaez.westernproject.Roles.Commands;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Roles.Sheriff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleCommands implements CommandExecutor {
    private Core plugin;

    public RoleCommands(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args[0].toLowerCase().compareTo("set") == 0) {
                if (args.length != 3) {
                    p.sendMessage(ChatColor.GRAY + "Incorrect usage. Please try " + ChatColor.BLUE + "/role set {player} {role}");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[1]);
                //This handles offline players, or misspelled players
                if (target == null) {
                    p.sendMessage(ChatColor.GRAY + "Player is not " + ChatColor.BLUE + "online");
                    return true;
                }
                if (args[2].toLowerCase().trim().compareTo("sheriff") == 0) {
                    Sheriff newSheriff = new Sheriff(target.getUniqueId().toString(), target.getDisplayName(), plugin);
                    newSheriff.addSheriff(target.getUniqueId().toString(), target.getDisplayName());
                    p.sendMessage("You are now a sheriff!");
                    return true;
                }
                // === NEW ROLE HERE ===
                p.sendMessage(ChatColor.GRAY + "Incorrect usage. Please try " + ChatColor.BLUE + "/role set {player} {role}");
                return true;
            }
            else if (args[0].toLowerCase().compareTo("list") == 0) {
                //fill
                return true;
            }
            else if (args[0].toLowerCase().compareTo("remove") == 0) {
                if (args.length != 3) {
                    p.sendMessage(ChatColor.GRAY + "Incorrect usage. Please try " + ChatColor.BLUE + "/role remove {player} {role}");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[1]);
                if (target == null) {
                    p.sendMessage(ChatColor.GRAY + "Player is not " + ChatColor.BLUE + "online");
                    System.out.println("loaded sheriff was not a player");
                    return true;
                }
                if (args[2].toLowerCase().trim().compareTo("sheriff") == 0) {
                    if (!Sheriff.isSheriff(target.getUniqueId().toString())) {
                        p.sendMessage(ChatColor.GRAY + "Player does not have the role " + ChatColor.BLUE + "Sheriff");
                        return true;
                    }
                    Sheriff pSheriff = Sheriff.getSheriff(target.getUniqueId().toString());
                    System.out.println(target.getUniqueId().toString() + "!!!!");
                    String test = target.getUniqueId().toString();
                    pSheriff.removeSheriff(test);
                    target.sendMessage("You are no longer a sheriff");
                }
                // === REMOVE NEW ROLE HERE ===
            }
            // === NEW COMMAND HERE ===

        }


        return true;
    }
}
