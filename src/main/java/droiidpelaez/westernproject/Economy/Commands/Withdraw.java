package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
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
                p.sendMessage(ChatColor.RED+ "Incorrect usage please try:");
                p.sendMessage(ChatColor.GRAY+ "/withdraw {amount}");
                return true;
            }
            Double amount = GlobalUtils.checkStrToDErrMsg(args[0], p);
            if(amount == -1.0){
                return true;
            }
            if(!BankAccountUtils.hasAccount(p)){
                p.sendMessage(ChatColor.RED+"Account not found.");
                return true;
            }
            if((BankAccountUtils.getPlayerFunds(p) - amount) >= 0.0){
                WalletUtils.updateBalance(p, amount);
                p.sendMessage(ChatColor.GREEN+"You withdrew "+amount+"g");
                BankAccountUtils.removeFunds(p, amount);
                return true;
            }

        }
        return true;
    }
}
