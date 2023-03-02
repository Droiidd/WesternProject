package droiidpelaez.westernproject.PlayerCore.Commands;

import droiidpelaez.westernproject.Items.HealthItems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Medkit implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player){
            Player p = (Player) sender;
            HealthItems meds = new HealthItems();
            ItemStack bandage = meds.getBandage();
            ItemStack splint = meds.getSplint();
            p.getInventory().addItem(bandage);
            p.getInventory().addItem(splint);
            p.sendMessage(ChatColor.RED+"Medkit"+ChatColor.RESET+" recieved!");

        }
        return true;
    }
}
