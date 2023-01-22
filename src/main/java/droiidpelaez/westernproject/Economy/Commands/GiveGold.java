package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.Economy.Utils.GoldUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveGold implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            //Usage:
            if(args.length == 0){
                p.sendMessage("========================================");
                p.sendMessage("Usage:\n/giveGold {player} {amount}");
                p.sendMessage("========================================");
                return true;
            }

            //Handles the /eco add ____ case : null player input
            if(args.length < 2 || args.length > 2){
                p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/eco add {Player} {Amount}");
                return true;
            }
            //This saves a player object of the target.
            Player target = Bukkit.getServer().getPlayer(args[0]);
            //This handles offline players, or misspelled players
            if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); return true;}
            //Creates a gold coin of desired input
            Double amount = GlobalUtils.checkStrToDErrMsg(args[1], target);
            ItemStack gold = GoldUtils.getNewCoin(amount);
            //Add it to the inv
            target.getPlayer().getInventory().addItem(gold);
            target.sendMessage(ChatColor.GREEN+"Given "+target.getPlayer().getDisplayName()+" "+amount+"g!");
            return true;
        }
        return true;
    }

}
