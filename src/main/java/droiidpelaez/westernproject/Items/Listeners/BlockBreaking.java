package droiidpelaez.westernproject.Items.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Items.MiningItems;
import droiidpelaez.westernproject.Items.PotionItems;
import droiidpelaez.westernproject.Items.Utils.MiningUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class BlockBreaking implements Listener {
    private Core plugin;
    private MiningItems miningItems = new MiningItems();

    public BlockBreaking(Core plugin) {
        this.plugin = plugin;
    }

    public void replaceRandomBlock(Material mainBlock, Material secondaryBlock, Location blockLocation, Player p) {
        MiningUtils mineUtils = new MiningUtils(plugin);
        int chance = new Random().nextInt(11);
        if (chance % 9 == 0) {
            mineUtils.startRespawnTimer(secondaryBlock, blockLocation);
        } else {
            mineUtils.startRespawnTimer(mainBlock, blockLocation);
        }
    }
    public boolean spawnGeode(Player p) {
        int chance = new Random().nextInt(11);
        if (chance % 9 == 0) {
            return true;
        }
        return false;
    }
    @EventHandler
    public void onGeodeBreak(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack geode = miningItems.getGeode();
            if (p.getInventory().getItemInMainHand().getType().equals(geode.getType())) {
                p.getInventory().removeItem(geode);
                p.sendMessage("give diamonds");
            }
        }
    }

    @EventHandler
    public void onPlayerMine(BlockBreakEvent e) {
        Player p = e.getPlayer();
        //ROLL A RANDOM NUMBER HERE AND GIVE THE PLAYER A GEODE INSTEAD IF THEY WIN

        //CASE: IRON ORE
        if (e.getBlock().getType().equals(Material.IRON_ORE)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack geode = miningItems.getGeode();
            ItemStack unRefinedIron = miningItems.getUnRefinedIron();
            //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 0) {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                }
            }
            //PLAYER IS NOT LUCKY
            else {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                }
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.IRON_ORE, Material.RAW_IRON_BLOCK, blockLocation, p);
        }
        //CASE: RAW IRON CLUSTER
        else if (e.getBlock().getType().equals(Material.RAW_IRON_BLOCK)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack geode = miningItems.getGeode();
            ItemStack unRefinedIron = miningItems.getUnRefinedIron();
            //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 0) {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                }
            }
            //PLAYER IS NOT LUCKY
            else {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedIron);
                }
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.IRON_ORE, Material.RAW_IRON_BLOCK, blockLocation, p);
        }
        //CASE: COPPER ORE
        else if (e.getBlock().getType().equals(Material.COPPER_ORE)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack geode = miningItems.getGeode();
            ItemStack unRefinedCopper = miningItems.getUnRefinedCopper();
            //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 0) {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                }
            }
            //PLAYER IS NOT LUCKY
            else {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                }
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.COPPER_ORE, Material.RAW_COPPER_BLOCK, blockLocation, p);
        }
        //CASE: COPPER ORE CLUSTER
        else if (e.getBlock().getType().equals(Material.RAW_COPPER_BLOCK)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack geode = miningItems.getGeode();
            ItemStack unRefinedCopper = miningItems.getUnRefinedCopper();
            //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 0) {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                }
            }
            //PLAYER IS NOT LUCKY
            else {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedCopper);
                }
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.COPPER_ORE, Material.RAW_COPPER_BLOCK, blockLocation, p);
        }
        //CASE: GOLD ORE
        else if (e.getBlock().getType().equals(Material.GOLD_ORE)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack geode = miningItems.getGeode();
            ItemStack unRefinedGold = miningItems.getUnRefinedGold();
            //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 0) {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);

                }
            }
            //PLAYER IS NOT LUCKY
            else {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                }
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.GOLD_ORE, Material.RAW_GOLD_BLOCK, blockLocation, p);
        }
        //CASE: GOLD ORE CLUSTER
        else if (e.getBlock().getType().equals(Material.RAW_GOLD_BLOCK)) {
            Location blockLocation = e.getBlock().getLocation();
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            ItemStack geode = miningItems.getGeode();
            ItemStack unRefinedGold = miningItems.getUnRefinedGold();
            //TESTING FOR PLAYER LUCK
            PotionEffect effect = p.getPotionEffect(PotionEffectType.LUCK);
            if (effect != null && effect.getAmplifier() == 0) {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                }
            }
            //PLAYER IS NOT LUCKY
            else {
                //IF IT IS A GEODE
                if (spawnGeode(p)) {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, geode);
                }
                //IT IS NOT A GEODE
                else {
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                    blockLocation.getWorld().dropItemNaturally(blockLocation, unRefinedGold);
                }
            }
            //Make it random 10% chance you get an iron cluster spawn for an iron ore
            //Then call the respawn timer
            replaceRandomBlock(Material.GOLD_ORE, Material.RAW_GOLD_BLOCK, blockLocation, p);
        }


    }


}
