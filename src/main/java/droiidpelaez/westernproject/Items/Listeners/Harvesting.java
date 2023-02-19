package droiidpelaez.westernproject.Items.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Items.DrugItems;
import droiidpelaez.westernproject.Items.Utils.CropUtils;
import droiidpelaez.westernproject.Items.Utils.MiningUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.SeaPickle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Harvesting implements Listener
{
    private DrugItems drugItems = new DrugItems();
    private Core plugin;

    public Harvesting(Core plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerHarvest(BlockBreakEvent e)
    {
        CropUtils cropUtils = new CropUtils(plugin);
        Player p = e.getPlayer();
        //CASE: Wildgrass
        if (e.getBlock().getType().equals(Material.SEA_PICKLE)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);

            ItemStack drug = drugItems.getWildgrass();
            SeaPickle seaPickle = (SeaPickle) e.getBlock().getBlockData();
            seaPickle.setWaterlogged(false);
            seaPickle.setPickles(1);
            e.getBlock().setBlockData(seaPickle);
                    //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 1) {
                //THEY RECEIVE DOUBLE
                p.getInventory().addItem(drug);
                p.getInventory().addItem(drug);
            }
            //PLAYER IS NOT LUCKY
            else {
                p.getInventory().addItem(drug);
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            cropUtils.startPickleRespawnTimer(seaPickle, blockLocation);
        }
        //CASE: Jack O Fruit
        else if (e.getBlock().getType().equals(Material.SWEET_BERRY_BUSH)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.SWEET_BERRY_BUSH);
            ItemStack drug = drugItems.getJackoFruit();
            //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 1) {
                //THEY RECEIVE DOUBLE
                p.getInventory().addItem(drug);
                p.getInventory().addItem(drug);
            }
            //PLAYER IS NOT LUCKY
            else {
                p.getInventory().addItem(drug);
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
           cropUtils.startCropRespawnTimer(Material.SWEET_BERRY_BUSH, blockLocation);
        }


    }


}
