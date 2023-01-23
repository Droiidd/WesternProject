package droiidpelaez.westernproject.Teams.PlayerCore.Commands;

import droiidpelaez.westernproject.Teams.PlayerCore.PlayerCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreDisplay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(!PlayerCore.hasPlayer(p)){
                p.sendMessage("No player found");
                return true;
            }
            PlayerCore pCore = PlayerCore.getPlayerCore(p);
            if(args.length == 0){
                p.sendMessage("Player Info:");
                p.sendMessage("--- --- ---");
                p.sendMessage("Bounty: "+pCore.getPlayerBounty(p));
                p.sendMessage("Bleeding: "+pCore.getPlayerBleedStat());
                p.sendMessage("Wanted: "+pCore.getPlayerWantedStat());
                return true;
            }
            if(args.length != 1){
                //handle error
                return true;
            }
            if(args[0].toLowerCase().compareTo("bleed") == 0){
                pCore.updateBleed(!pCore.getPlayerBleedStat());
                p.sendMessage("Stat updated.");
            }




        }



        return true;
    }
}
