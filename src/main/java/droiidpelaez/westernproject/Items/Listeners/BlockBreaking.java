package droiidpelaez.westernproject.Items.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Items.MiningItems;
import droiidpelaez.westernproject.Items.Utils.MiningUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreaking implements Listener {
    private Core plugin;
    private MiningItems miningItems = new MiningItems();
    public BlockBreaking(Core plugin)
    {
        this.plugin = plugin;
    }



    @EventHandler
    public void onPlayerMine(BlockBreakEvent e){
        MiningUtils mineUtils = new MiningUtils(plugin);
        Player p = e.getPlayer();
        //ROLL A RANDOM NUMBER HERE AND GIVE THE PLAYER A GEODE INSTEAD IF THEY WIN
        if(e.getBlock().getType().equals(Material.IRON_ORE)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack unRefinedIron = miningItems.getUnRefinedIron();
            p.getInventory().addItem(unRefinedIron);
            p.sendMessage("Broke iron");
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.IRON_ORE, blockLocation);
        }
        else if(e.getBlock().getType().equals(Material.RAW_COPPER_BLOCK)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack unRefinedIron = miningItems.getUnRefinedIronCluster();
            p.getInventory().addItem(unRefinedIron);
            p.sendMessage("Broke iron");
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            mineUtils.startRespawnTimer(Material.IRON_ORE, blockLocation);
        }



    }


}
