package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Utils.Wallet;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckWallet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length>0){
                p.sendMessage(ChatColor.RED+ "Incorrect usage please try:");
                p.sendMessage(ChatColor.GRAY+ "/wallet or /wa");
                return true;
            }
            p.sendMessage(ChatColor.GRAY+"Wallet: "+ChatColor.GOLD+ Wallet.getPlayerFunds(p)+"g");
        }
        return true;
    }
}
