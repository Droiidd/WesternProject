package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.Economy.Utils.GoldUtils;
import droiidpelaez.westernproject.Economy.Wallet;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropGold implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Double dropAmount = GlobalUtils.checkStrToDErrMsg(args[0], p);
            if(dropAmount == -1.0){
                p.sendMessage(ChatColor.GRAY+"Invalid amount."+ChatColor.DARK_GREEN+" Please try again");
                return true;
            }
            Double playerWallet = Wallet.getPlayerFunds(p);
            if(playerWallet - dropAmount < 0){
                p.sendMessage(ChatColor.DARK_GREEN+"Not enough funds!");
                p.sendMessage(ChatColor.DARK_GREEN+"/wallet "+ChatColor.GRAY+"to check funds");
                return true;
            }

            Wallet.removeMoney(p, dropAmount);
            ItemStack goldAmount = GoldUtils.getNewCoin(dropAmount);
            p.getWorld().dropItemNaturally(p.getLocation(), goldAmount);
        }
        return true;
    }
}
