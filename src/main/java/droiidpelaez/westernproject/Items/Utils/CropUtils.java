package droiidpelaez.westernproject.Items.Utils;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.SeaPickle;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;
import java.util.regex.Pattern;

public class CropUtils
{
    private Core plugin;

    public CropUtils(Core plugin)
    {
        this.plugin = plugin;
    }

    public void startCropRespawnTimer(Material blockType, Location blockLoc)
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
                Block harvestedBlock = blockLoc.getBlock();
                blockLoc.getBlock().setType(blockType);//Replaces the stone to its previous ore block

                Ageable agedBlock = (Ageable) harvestedBlock.getState().getBlockData();
                agedBlock.setAge(agedBlock.getMaximumAge());

                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_AZALEA_LEAVES_PLACE, 1, 0);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_CAVE_VINES_PLACE, 1, 0);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_BAMBOO_HIT, 1, 0);
                GlobalUtils globalUtils = new GlobalUtils();
                globalUtils. displayParticles(blockLoc, Particle.COMPOSTER, Particle.VILLAGER_HAPPY, 5);
                harvestedBlock.setBlockData(agedBlock);
                Bukkit.getScheduler().cancelTask(id); //Use to cancel the runnable
            }
        }, 240); //(This is the amount of seconds it takes )
    }


    public void startPickleRespawnTimer(SeaPickle seaPickle, Location blockLoc)
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
                Block harvestedBlock = blockLoc.getBlock();
                seaPickle.setPickles(seaPickle.getMaximumPickles());
                harvestedBlock.setBlockData(seaPickle);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_AZALEA_LEAVES_PLACE, 1, 0);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_WART_BLOCK_PLACE, 1, 0);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_BEEHIVE_DRIP, 1, 1);
                GlobalUtils globalUtils = new GlobalUtils();
                globalUtils. displayParticles(blockLoc, Particle.COMPOSTER, Particle.VILLAGER_HAPPY, 5);
                Bukkit.getScheduler().cancelTask(id); //Use to cancel the runnable
            }
        }, 240); //(This is the amount of seconds it takes )
    }
    public void startFlowerRespawnTimer(Material blockType, Location blockLoc)
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


                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.ENTITY_GLOW_SQUID_AMBIENT, 1, 1);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 2, 1);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_AZALEA_LEAVES_PLACE, 1, 0);
                Bukkit.getServer().getWorld("WesternProject").playSound(blockLoc, Sound.BLOCK_SWEET_BERRY_BUSH_PLACE, 1, 1);
                GlobalUtils globalUtils = new GlobalUtils();
                globalUtils.displayParticles(blockLoc, Particle.GLOW, Particle.TOTEM, 8);

                Bukkit.getScheduler().cancelTask(id); //Use to cancel the runnable
            }
        }, 240); //(This is the amount of seconds it takes )
    }
}
