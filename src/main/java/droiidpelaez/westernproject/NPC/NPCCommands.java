package droiidpelaez.westernproject.NPC;

import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NPCCommands implements CommandExecutor
{
    public void createVillager(Player p, String villagerName)
    {
        Villager vil = (Villager)p.getLocation().getWorld().spawn(p.getLocation(), Villager.class);
        vil.setCustomName(villagerName);
        vil.setCustomNameVisible(true);
        vil.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 127));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player){
           Player p = (Player) sender;
           if(args.length != 1)
           {
               p.sendMessage("Incorrect");
               return false;
           }
           String userIn = args[0].toLowerCase().trim();
            GlobalUtils globalUtils = new GlobalUtils();

           switch(userIn){
               case "geologist":
                   String villagerName = ChatColor.BLUE+""+ChatColor.BOLD+"Geologist";
                   p.sendMessage("Spawned "+villagerName+"!");
                   p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1 , 0);
                   globalUtils.displayParticles(p.getLocation(), Particle.CLOUD, Particle.GLOW,5);
                   createVillager(p, villagerName);
                   break;
           }





         }




        return true;
    }
}
