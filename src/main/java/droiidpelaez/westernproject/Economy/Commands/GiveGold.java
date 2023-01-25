package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.UtilCore.GlobalUtils;
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
                p.sendMessage("");
                p.sendMessage(ChatColor.GRAY+"==========");
                p.sendMessage(ChatColor.GRAY+ "Usage:\n"+ChatColor.DARK_GREEN+" /giveGold {player} {amount}");
                p.sendMessage(ChatColor.GRAY+"==========");
                return true;
            }

            //Handles the /eco add ____ case : null player input
            if(args.length < 2 || args.length > 2){
                p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_GREEN+"/givegold {user} {amount}");
                return true;
            }
            //This saves a player object of the target.
            Player target = Bukkit.getServer().getPlayer(args[0]);
            //This handles offline players, or misspelled players
            if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not "+ChatColor.DARK_GREEN+"online."); return true;}
            //Creates a gold coin of desired input
            Double amount = GlobalUtils.checkStrToDErrMsg(args[1], target);
            if(amount == -1.0){
                p.sendMessage(ChatColor.GRAY+"Invalid amount."+ChatColor.DARK_GREEN+" Please try again");
                return true;
            }
            ItemStack gold = GoldUtils.getNewCoin(amount);
            //Add it to the inv
            p.sendMessage(ChatColor.GRAY+"Given "+ChatColor.GRAY+target.getDisplayName()+ChatColor.GOLD+" "+amount+"g");
            target.getPlayer().getInventory().addItem(gold);
            target.sendMessage(ChatColor.GRAY+"Given "+target.getPlayer().getDisplayName()+ChatColor.GOLD+ " "+amount+"g!");
            return true;
        }
        return true;
    }

}
