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

import java.util.Random;

public class BlockBreaking implements Listener {
    private Core plugin;
    private MiningItems miningItems = new MiningItems();
    public BlockBreaking(Core plugin)
    {
        this.plugin = plugin;
    }

    public void replaceRandomBlock(Material mainBlock, Material secondaryBlock, Location blockLocation,Player p)
    {
        MiningUtils mineUtils = new MiningUtils(plugin);
        int chance = new Random().nextInt(11);
        if(chance%9==0){
            mineUtils.startRespawnTimer(secondaryBlock, blockLocation);
        }
        else{
            mineUtils.startRespawnTimer(mainBlock, blockLocation);
        }

    }




    @EventHandler
    public void onPlayerMine(BlockBreakEvent e){
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
            replaceRandomBlock(Material.IRON_ORE,Material.RAW_IRON_BLOCK,blockLocation,p);

        }
        else if(e.getBlock().getType().equals(Material.RAW_IRON_BLOCK)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack unRefinedIronCluster = miningItems.getUnRefinedIronCluster();
            p.getInventory().addItem(unRefinedIronCluster);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.IRON_ORE,Material.RAW_IRON_BLOCK,blockLocation,p);
        }
        else if(e.getBlock().getType().equals(Material.COPPER_ORE)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack unRefinedCopper = miningItems.getUnRefinedCopper();
            p.getInventory().addItem(unRefinedCopper);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.COPPER_ORE,Material.RAW_COPPER_BLOCK,blockLocation,p);
        }
        else if(e.getBlock().getType().equals(Material.RAW_COPPER_BLOCK)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack unRefinedCopperCluster = miningItems.getUnRefinedCopperCluster();
            p.getInventory().addItem(unRefinedCopperCluster);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.COPPER_ORE,Material.RAW_COPPER_BLOCK,blockLocation,p);
        }
        else if(e.getBlock().getType().equals(Material.GOLD_ORE)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack unRefinedGold = miningItems.getUnRefinedGold();
            p.getInventory().addItem(unRefinedGold);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.GOLD_ORE,Material.RAW_GOLD_BLOCK,blockLocation,p);
        }
        else if(e.getBlock().getType().equals(Material.RAW_GOLD_BLOCK)){
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack unRefinedGoldCluster = miningItems.getUnRefinedGoldCluster();
            p.getInventory().addItem(unRefinedGoldCluster);
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.GOLD_ORE,Material.RAW_GOLD_BLOCK,blockLocation,p);
        }



    }


}
