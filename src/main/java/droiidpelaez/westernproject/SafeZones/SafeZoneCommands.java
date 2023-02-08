package droiidpelaez.westernproject.SafeZones;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SafeZoneCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
//            if(args.length >= 1 && args.length <= 2){
//                p.sendMessage("incorrect");
//                return true;
//            }
            if(args[0].toLowerCase().compareTo("test")==0){
                SafeZone sf = SafeZone.getSafeZone("test");
                Double xpos1 = sf.getxPos1();
                Double xpos2 = sf.getxPos2();
                Double zpos1 = sf.getzPos1();
                Double zpos2 = sf.getzPos2();
                p.sendMessage("Cords:");
                p.sendMessage(""+xpos1+" "+zpos1+"");
                p.sendMessage(""+xpos2+" "+zpos2+"");
                double tempPlayX = p.getLocation().getX();
                double tempPlayZ = p.getLocation().getZ();
                Double playerX = new Double(tempPlayX);
                Double playerZ = new Double(tempPlayZ);

                Double minX;
                Double maxX;
                Double minZ;
                Double maxZ;

                if(xpos1 < xpos2){
                    minX = xpos1;
                    maxX = xpos2;
                }
                else{
                    minX = xpos2;
                    maxX = xpos1;
                }

                if(zpos1 < zpos2){
                    minZ = zpos1;
                    maxZ = zpos2;
                }
                else{
                    minZ = zpos2;
                    maxZ = zpos1;
                }

                if(playerX > minX && playerX < maxX){
                    if(playerZ > minZ && playerZ < maxZ){
                        p.sendMessage("WOOOOO");
                        return true;
                    }
                }
                else{
                    p.sendMessage("no woo....");
                    return true;
                }
                return true;
            }
            if(args[0].toLowerCase().compareTo("create") == 0){
                p.sendMessage("created");
                SafeZoneGenerator safeZoneGenerator = new SafeZoneGenerator();
                String zoneName = args[1].toLowerCase();
                p.sendMessage("zone name:" +zoneName);
                p.sendMessage("Test" +safeZoneGenerator.getPos1(zoneName)+" "+
                        safeZoneGenerator.getPos2(zoneName));
                if(safeZoneGenerator.isZoneInitialized(zoneName)){
                    p.sendMessage("Zone is true");
                    Double pos1 = safeZoneGenerator.getPos1(zoneName);
                    Double pos2 = safeZoneGenerator.getPos2(zoneName);
                    if(pos2 - pos1 > 0){
                        if(p.getLocation().getX() < pos2 && p.getLocation().getX() > pos1 ){
                            p.sendMessage("You are in the zone!");
                        }else{
                            p.sendMessage("You are not in the zone");
                        }
                        return true;

                    }
                    else{
                        if(p.getLocation().getX() > pos2 && p.getLocation().getX() < pos1 ){
                            p.sendMessage("You are in the zone!");
                        }else{
                            p.sendMessage("You are not in the zone");
                        }
                        return true;
                    }
                }
                else{
                    p.sendMessage("zone is not true for some reson");
                }
                return true;
            }




        }





        return true;
    }
}
