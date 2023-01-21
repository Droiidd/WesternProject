package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.Economy.Utils.GoldUtils;
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
            ItemStack goldAmount = GoldUtils.getNewCoin(dropAmount);
            p.getWorld().dropItemNaturally(p.getLocation(), goldAmount);
        }





        return true;
    }
}
