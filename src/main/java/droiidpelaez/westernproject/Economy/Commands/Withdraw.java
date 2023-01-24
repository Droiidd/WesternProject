package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.Economy.Wallet;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Withdraw implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length != 1){
                p.sendMessage(ChatColor.GRAY+ "Incorrect usage please try:");
                p.sendMessage(ChatColor.DARK_GREEN+ "/withdraw {amount}");
                return true;
            }
            Double amount = GlobalUtils.checkStrToDErrMsg(args[0], p);
            if(amount == -1.0){
                p.sendMessage(ChatColor.GRAY+"Invalid amount."+ChatColor.DARK_GREEN+" Please try again");
                return true;
            }
            if(!Bank.hasAccount(p)){
                p.sendMessage(ChatColor.RED+"Account not found.");
                return true;
            }
            if((Bank.getPlayerFunds(p) - amount) >= 0.0){
                Wallet.updateBalance(p, amount);
                p.sendMessage(ChatColor.GRAY+"You withdrew "+ChatColor.GOLD+ amount+"g");
                Bank.removeFunds(p, amount);
                return true;
            }

        }
        return true;
    }
}
