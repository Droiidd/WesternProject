package droiidpelaez.westernproject.PlayerCore.Commands;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SkullCommand implements CommandExecutor {
    private Core plugin;
    public SkullCommand(Core plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                //give p their head
                //p.getInventory().addItem(getPlayerHead(p.getDisplayName()));
                return true;
            }
            else if(args.length == 1){
                //They want a players head
                Player target = GlobalUtils.getPlayerFromString(args[0]);
                if(target == null){
                    return true;
                }
            }

        }


        return true;
    }
}
















