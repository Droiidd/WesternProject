package droiidpelaez.westernproject.PlayerCore.Commands;

import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreDisplay implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(!PlayerCore.hasPlayer(p.getUniqueId().toString())){
                p.sendMessage("No player found");
                return true;
            }
            PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
            if(args.length == 0){
                p.sendMessage("Player Info:");
                p.sendMessage("--- --- ---");
                p.sendMessage("Bounty: "+pCore.getPlayerBounty());
                p.sendMessage("Bleeding: "+pCore.isPlayerBleeding());
                p.sendMessage("Wanted: "+pCore.isPlayerWanted());
                return true;
            }
            if(args.length != 1){
                //handle error
                return true;
            }
            if(args[0].toLowerCase().compareTo("bleed") == 0){
                pCore.updateOnlineBleed(p, !pCore.isPlayerBleeding());
                p.sendMessage("Stat updated.");
            }
        }
        return true;
    }
}
