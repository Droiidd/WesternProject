package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WalletCommands implements CommandExecutor {

    private static HashMap<String, Double> walletList = WalletUtils.getWallets();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length>0){
                p.sendMessage(ChatColor.RED+ "Incorrect usage please try:");
                p.sendMessage(ChatColor.GRAY+ "/wallet or /wa");
                return true;
            }
            if(args.length == 0){
                if(walletList.containsKey(p.getUniqueId().toString()) != true){ WalletUtils.createWallet(p);}
                else
                    p.sendMessage(ChatColor.GRAY+"Wallet: $"+ChatColor.GOLD+walletList.get(p.getUniqueId().toString()));
            }
        }



        return true;
    }
}
