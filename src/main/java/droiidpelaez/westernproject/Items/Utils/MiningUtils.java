package droiidpelaez.westernproject.Items.Utils;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public class MiningUtils
{
    private Core plugin;
    private int count;

    public MiningUtils(Core plugin)
    {
        this.plugin = plugin;
        count = 0;
    }


    public void startRespawnTimer(Material blockType, Location blockLoc)
    {
        BukkitScheduler schedular = Bukkit.getServer().getScheduler();

        int id = schedular.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                //Wait here for 12 seconds (240/20 = 1 second)
            }
        }, 0,10);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {

                blockLoc.getBlock().setType(blockType); //Replaces the stone to its previous ore block
                int chance = new Random().nextInt(11);
                if (chance % 2 == 0) {
                    Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.ENTITY_VILLAGER_WORK_MASON, 1, 1);
                }else{
                    Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.ENTITY_VILLAGER_WORK_MASON, 1, 0);
                }
                    Bukkit.getScheduler().cancelTask(id); //Use to cancel the runnable
            }
        }, 240); //(This is the amount of seconds it takes )
    }







}
