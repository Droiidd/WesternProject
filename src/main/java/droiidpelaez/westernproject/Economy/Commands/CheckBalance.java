package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CheckBalance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                //This handles offline players, or misspelled players
                if (target == null) {
                    p.sendMessage(ChatColor.GRAY + "This player is not online.");
                    return true;
                }
                p.sendMessage(target.getDisplayName() + "'s balance  :  $" + BankAccountUtils.getPlayerFunds(target));
                return true;
            }
            p.sendMessage(p.getDisplayName() + "'s balance  :  $" + BankAccountUtils.getPlayerFunds(p));
        }
        return true;
    }
}
