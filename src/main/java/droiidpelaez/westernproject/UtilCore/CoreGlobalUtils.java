package droiidpelaez.westernproject.UtilCore;

import droiidpelaez.westernproject.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class CoreGlobalUtils
{
    final private Core plugin;
    private int ticks = 0;
    private int seconds = 0;
    public CoreGlobalUtils(Core plugin)
    {
        this.plugin = plugin;
    }
    public void fastTravelPlayer(Player p, Location destination)
    {
        Location loc = new Location(p.getWorld(),1115, 95, -807 );
        p.teleport(loc);
        BukkitScheduler schedular = Bukkit.getServer().getScheduler();
        int id = schedular.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(ticks % 2 == 0){
                    //Time executed in seconds
                    seconds++;
                    if(seconds % 2 == 0){
                        //Time is executed in every other second
                        p.playSound(p.getLocation(), Sound.ENTITY_MINECART_RIDING, 1, 1);
                    }

                }
                ticks++;
                //Wait here for 12 seconds (240/20 = 1 second)
            }
        }, 0,10);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                p.teleport(destination);
                p.sendMessage(ChatColor.GRAY+"Conductor: We've arrived, please depart the train");
                Bukkit.getScheduler().cancelTask(id); //Use to cancel the runnable
            }
        }, 240);

    }
}
