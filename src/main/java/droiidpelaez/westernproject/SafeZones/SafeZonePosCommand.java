package droiidpelaez.westernproject.SafeZones;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SafeZonePosCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length != 2){
                p.sendMessage("incorrect");
                return true;
            }
            SafeZoneGenerator szGenerator = new SafeZoneGenerator();
            if(args[0].toLowerCase().trim().compareTo("pos1") == 0){
                Double pos1 = p.getLocation().getX();
                String zoneName = args[1].toLowerCase().trim();
                szGenerator.setPos1(zoneName, pos1);
                p.sendMessage("pos1:" +pos1);
                p.sendMessage("name: "+zoneName+".");
                return true;
            }
            if(args[0].toLowerCase().trim().compareTo("pos2") == 0){
                Double pos2 = p.getLocation().getX();
                String zoneName = args[1].toLowerCase().trim();
                szGenerator.setPos2(zoneName, pos2);
                p.sendMessage("pos2:" +pos2);
                p.sendMessage("name: "+zoneName+".");
                return true;
            }





        }





        return true;
    }
}
