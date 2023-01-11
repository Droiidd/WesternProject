package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CheckBalance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            HashMap<String,Double> playerBank = BankAccountUtils.getBankList();
            if(playerBank.containsKey(p.getUniqueId().toString()) != true){ BankAccountUtils.createBankAccount(p);}
            else

                p.sendMessage(p.getDisplayName()+"'s balance  :  $"+ playerBank.get(p.getUniqueId().toString()));
        }
        return true;
    }
}
