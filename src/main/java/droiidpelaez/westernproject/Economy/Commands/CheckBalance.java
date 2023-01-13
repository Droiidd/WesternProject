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
            HashMap<String, Double> bankList = BankAccountUtils.getBankList();

            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                //This handles offline players, or misspelled players
                if (target == null) {
                    p.sendMessage(ChatColor.GRAY + "This player is not online.");
                    return true;
                }
                if (bankList.containsKey(target.getUniqueId().toString()) != true) {
                    BankAccountUtils.createBankAccount(target);
                }
                p.sendMessage(target.getDisplayName() + "'s balance  :  $" + bankList.get(target.getUniqueId().toString()));
                return true;
            }


            if (bankList.containsKey(p.getUniqueId().toString()) != true) {
                BankAccountUtils.createBankAccount(p);
            }


            p.sendMessage(p.getDisplayName() + "'s balance  :  $" + bankList.get(p.getUniqueId().toString()));
        }
        return true;
    }
}
