package droiidpelaez.westernproject.Items.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Items.ForageItems;
import droiidpelaez.westernproject.Items.Utils.MiningUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Foraging implements Listener
{
    private ForageItems forageItems = new ForageItems();
    private Core plugin;

    public Foraging(Core plugin)
    {
        this.plugin = plugin;
    }


    @EventHandler
    public void onForage(BlockBreakEvent e)
    {
        MiningUtils mineUtils = new MiningUtils(plugin);
        Player p = e.getPlayer();
        //MOLES BREATH FUNGI -> MOLES BREATH SPORES
        if(e.getBlock().getType().equals(Material.WARPED_FUNGUS)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.NETHER_SPROUTS);
            ItemStack foragedItem = forageItems.getMolesBreathSpores();
            p.getInventory().addItem(foragedItem);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.WARPED_FUNGUS, blockLocation);
        }
        //GOLDEN GAMBLE -> GOLDEN GAMBLE PETAL
        else if(e.getBlock().getType().equals(Material.ORANGE_TULIP)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.MANGROVE_PROPAGULE);
            ItemStack foragedItem = forageItems.getGoldenGamblePetal();
            p.getInventory().addItem(foragedItem);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.ORANGE_TULIP, blockLocation);
        }
        //Life Flower -> Life Petal
        else if(e.getBlock().getType().equals(Material.LILY_OF_THE_VALLEY)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.MANGROVE_PROPAGULE);
            ItemStack foragedItem = forageItems.getLifePetal();
            p.getInventory().addItem(foragedItem);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.LILY_OF_THE_VALLEY, blockLocation);
        }
        //NIGHT SHADE -> STAR PETAL
        else if(e.getBlock().getType().equals(Material.CORNFLOWER)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.MANGROVE_PROPAGULE);
            ItemStack foragedItem = forageItems.getStarPetal();
            p.getInventory().addItem(foragedItem);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.CORNFLOWER, blockLocation);
        }
        //Passion Flowers -> Frenzied Stems
        else if(e.getBlock().getType().equals(Material.ROSE_BUSH)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.SWEET_BERRY_BUSH);
            ItemStack foragedItem = forageItems.getFrenziedStems();
            p.getInventory().addItem(foragedItem);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.ROSE_BUSH, blockLocation);
        }
        //Heart fruit flower -> Heart Fruit
        else if(e.getBlock().getType().equals(Material.POPPY)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.MANGROVE_PROPAGULE);
            ItemStack foragedItem = forageItems.getHeartFruit();
            p.getInventory().addItem(foragedItem);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.POPPY, blockLocation);
        }
    }
}
