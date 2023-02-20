package droiidpelaez.westernproject.NPC.Listeners;

import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.Items.GeodeController;
import droiidpelaez.westernproject.Items.MiningItems;
import droiidpelaez.westernproject.Items.Tools.Tools;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class NPCguiController implements Listener
{
    public void notEnoughFundsMsg(Player p, String npcName)
    {
        p.sendMessage(npcName+ChatColor.GRAY+"You do not have enough "+ChatColor.RED+"Funds!");
    }
    @EventHandler
    public void salesmenHandler(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE+"Geologist - "+ ChatColor.GRAY+"For Sale:")){
            Tools tools = new Tools();
            MiningItems miningItems = new MiningItems();
            switch(e.getCurrentItem().getType()){
                case BARRIER:
                    p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"Please, come again");
                    p.closeInventory();
                    break;
                case DIAMOND_PICKAXE:
                    if(p.getInventory().contains(miningItems.getRiverDiamond(), 5)){
                        if(Wallet.getPlayerFunds(p)> 1600.0){
                            //ACCPET USER TRANSACTION
                            for(int i = 0; i < 5; i++){
                                p.getInventory().remove(miningItems.getRiverDiamond());
                            }
                            Wallet.removeMoney(p,1600.0);
                            //GIVE USER THEIR ITEMS
                            ItemStack explorerPick = tools.getExplorerPick();
                            p.getInventory().addItem(explorerPick);
                            p.closeInventory();
                            p.sendMessage(ChatColor.GREEN+"Forged "+ChatColor.WHITE+"Explorers Pick "+ChatColor.GREEN+"1600.0g");
                        }else{
                            notEnoughFundsMsg(p,ChatColor.BLUE+"Geologist: ");
                        }
                    }
                    else{
                        p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"You do not have enough "+ChatColor.RED+"River Diamonds!");
                    }
                    break;
                case IRON_PICKAXE:
                    if(Wallet.getPlayerFunds(p)>=800.0){
                       //Make sure they can afford
                        Wallet.removeMoney(p,800.0);

                        //Giver user purchased Item
                        ItemStack oldMinersPick = tools.getOldMinersPick();
                        p.getInventory().addItem(oldMinersPick);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"Purchased "+ChatColor.WHITE+"Old Miner's Pick "+ChatColor.GOLD+"800.0g");
                        }else{
                        notEnoughFundsMsg(p,ChatColor.BLUE+"Geologist: ");
                    }
                    break;
                case STONE_PICKAXE:
                    //sell item
                    if(Wallet.getPlayerFunds(p)>=200.0){
                        //Make sure they can afford
                        Wallet.removeMoney(p,200.0);

                        //Giver user purchased Item
                        ItemStack brokenPick = tools.getBrokenPick();
                        p.getInventory().addItem(brokenPick);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"Purchased "+ChatColor.WHITE+"Broken Pick "+ChatColor.GOLD+"200.0g");
                    }else{
                        notEnoughFundsMsg(p,ChatColor.BLUE+"Geologist: ");
                    }
                    break;
                case NETHERITE_AXE:
                    //Break geode here
                    if(Wallet.getPlayerFunds(p)>150.0){
                        ItemStack geode = miningItems.getGeode();
                        GeodeController geoController = new GeodeController();
                        GlobalUtils globalUtils = new GlobalUtils();
                        for(int j =0;j<64;j++){
                            if(p.getInventory().contains(geode, j)){
                                p.getInventory().removeItem(geode);
                                Wallet.removeMoney(p, 50.0);
                                ArrayList<ItemStack> geodeContents = geoController.openGeode();
                                for(int i =0;i<geodeContents.size();i++){
                                    p.getInventory().addItem(geodeContents.get(i));
                                }

                                p.closeInventory();
                                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_HIT, 1,0);
                                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1,0);
                                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_COMPOSTER_FILL, 2,0);
                                globalUtils.displayParticles(p.getLocation(), Particle.ELECTRIC_SPARK, Particle.CLOUD, 5);
                                p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"Cracked "+ChatColor.DARK_GREEN+"Geode");
                            }
                        }

                    }else{
                        notEnoughFundsMsg(p,ChatColor.BLUE+"Geologist: ");
                    }
            }
            e.setCancelled(true);
        }

    }



}
