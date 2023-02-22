package droiidpelaez.westernproject.NPC.Listeners;

import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.Items.GeodeController;
import droiidpelaez.westernproject.Items.MiningItems;
import droiidpelaez.westernproject.Items.Tools.Tools;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class NPCguiController implements Listener
{
    private HashMap<String, Boolean> chatListener = new HashMap<>();
    private HashMap<String, Integer> bankListener = new HashMap<>();
    //private GlobalUtils gUtils = new GlobalUtils();
    private String geologistName = ChatColor.BLUE+""+ ChatColor.BOLD+"Geologist";
    private String bankerName = ChatColor.GOLD+""+ChatColor.BOLD+"Banker";


    public void msgNotEnoughFunds(Player p, String npcName)
    {
        p.sendMessage(npcName+": "+ChatColor.GRAY+"You do not have enough "+ChatColor.RED+"Funds!");
    }
    public void msgThanksForShopping(Player p, String npcName)
    {
        p.sendMessage(npcName+": "+ChatColor.GRAY+"Please, come again");
    }
    @EventHandler
    public void chatSaleHandler(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        Bank bank = Bank.getPlayerBank(p);
        Wallet wallet = Wallet.getPlayerWallet(p);
        p.sendMessage("Chat = " + chatListener.get(p.getUniqueId().toString()));
        if(chatListener.containsKey(p.getUniqueId().toString())){
            if(chatListener.get(p.getUniqueId().toString())){
                e.setCancelled(true);
                Double amount = GlobalUtils.checkStrToDErrMsg(e.getMessage(), p);
                if(amount == -1){
                    p.sendMessage(bankerName+ChatColor.GRAY+ ": That's not right...");
                    chatListener.replace(p.getUniqueId().toString(), false);
                }
                else{
                    if(bankListener.containsKey(p.getUniqueId().toString())){
                        switch(bankListener.get(p.getUniqueId().toString())){
                            case 1:
                                //WITHDRAWAL
                                if(bank.getPlayerFunds(p) -amount >= 0.0){

                                    Bukkit.getServer().broadcastMessage("HOLY FUCK!!");

                                    //bankListener.remove(p.getUniqueId().toString());
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    bank.removeFunds(p, amount);
                                    p.sendMessage(bankerName+ChatColor.GRAY+ ": Withdrew "+amount+ChatColor.GOLD+"g");

                                    Bukkit.getServer().broadcastMessage("HOLY FUCK");

                                }else{
                                    p.sendMessage(bankerName+ChatColor.GRAY+ ": Not enough funds.");
                                    //bankListener.remove(p.getUniqueId().toString());
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    //remove all lists
                                }
                                break;

                            case 2:
                                if(wallet.getPlayerFunds(p) >= amount){
                                    wallet.removeMoney(p, amount);
                                    bank.updateBalance(p, amount);
                                    //bankListener.remove(p.getUniqueId().toString());
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    p.sendMessage(bankerName+ChatColor.GRAY+ ": Deposited "+amount+ChatColor.GOLD+"g");

                                }else{
                                    p.sendMessage(bankerName+ChatColor.GRAY+ ": Not enough funds.");
                                    //bankListener.remove(p.getUniqueId().toString());
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    //remove all lists
                                }

                        }
                    }
                }

            }

           //player is not interacting with NPC
        }

    }
    @EventHandler
    public void salesmenHandler(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        Bank bank = Bank.getPlayerBank(p);
        Wallet wallet = Wallet.getPlayerWallet(p);
        Tools tools = new Tools();
        MiningItems miningItems = new MiningItems();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE+"Geologist - "+ ChatColor.GRAY+"For Sale:")){
            e.setCancelled(true);
            switch(e.getCurrentItem().getType()){
                case BARRIER:
                    msgThanksForShopping(p, geologistName);
                    p.closeInventory();
                    break;
                case DIAMOND_PICKAXE:
                    if(p.getInventory().contains(miningItems.getRiverDiamond(), 5)){
                        if(wallet.getPlayerFunds(p)> 1600.0){
                            //ACCPET USER TRANSACTION
                            for(int i = 0; i < 5; i++){
                                p.getInventory().remove(miningItems.getRiverDiamond());
                            }
                            wallet.removeMoney(p,1600.0);
                            //GIVE USER THEIR ITEMS
                            ItemStack explorerPick = tools.getExplorerPick();
                            p.getInventory().addItem(explorerPick);
                            p.closeInventory();
                            p.sendMessage(ChatColor.GREEN+"Forged "+ChatColor.WHITE+"Explorers Pick "+ChatColor.GREEN+"1600.0g");
                        }else{
                            msgNotEnoughFunds(p,geologistName);
                        }
                    }
                    else{
                        p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"You do not have enough "+ChatColor.RED+"River Diamonds!");
                    }
                    break;
                case IRON_PICKAXE:
                    if(wallet.getPlayerFunds(p)>=800.0){
                       //Make sure they can afford
                        wallet.removeMoney(p,800.0);

                        //Giver user purchased Item
                        ItemStack oldMinersPick = tools.getOldMinersPick();
                        p.getInventory().addItem(oldMinersPick);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"Purchased "+ChatColor.WHITE+"Old Miner's Pick "+ChatColor.GOLD+"800.0g");
                        }else{
                        msgNotEnoughFunds(p,geologistName);
                    }
                    break;
                case STONE_PICKAXE:
                    //sell item
                    if(wallet.getPlayerFunds(p)>=200.0){
                        //Make sure they can afford
                        wallet.removeMoney(p,200.0);

                        //Giver user purchased Item
                        ItemStack brokenPick = tools.getBrokenPick();
                        p.getInventory().addItem(brokenPick);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE+"Geologist: "+ChatColor.GRAY+"Purchased "+ChatColor.WHITE+"Broken Pick "+ChatColor.GOLD+"200.0g");
                    }else{
                        msgNotEnoughFunds(p,geologistName);
                    }
                    break;
                case NETHERITE_AXE:
                    //Break geode here
                    if(wallet.getPlayerFunds(p)>150.0){
                        ItemStack geode = miningItems.getGeode();
                        GeodeController geoController = new GeodeController();
                        GlobalUtils globalUtils = new GlobalUtils();
                        for(int j =0;j<64;j++){
                            //Checking if a player has geodes of any quantity
                            if(p.getInventory().contains(geode, j)){
                                p.getInventory().removeItem(geode);
                                wallet.removeMoney(p, 50.0);
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
                        msgNotEnoughFunds(p,geologistName);
                    }
            }

        }
        // >>>====--- BANKER ---===<<<
        else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GRAY+p.getName()+"'s Bank Account")){
            e.setCancelled(true);
            switch(e.getCurrentItem().getType()){
                case BARRIER:
                    p.closeInventory();
                    msgThanksForShopping(p, bankerName);
                    break;
                case NETHERITE_BLOCK:
                    p.closeInventory();
                    Integer withdraw = 1;
                    if(chatListener.containsKey(p.getUniqueId().toString())){
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if(bankListener.containsKey(p.getUniqueId().toString())){
                        bankListener.replace(p.getUniqueId().toString(), withdraw);
                    }
                    bankListener.put(p.getUniqueId().toString(), withdraw);
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(bankerName+ChatColor.GRAY+ ": please enter withdrawal amount");
                    break;
                case EMERALD_BLOCK:
                    p.closeInventory();
                    Integer deposit = 2;
                    if(chatListener.containsKey(p.getUniqueId().toString())){
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if(bankListener.containsKey(p.getUniqueId().toString())){
                        bankListener.replace(p.getUniqueId().toString(), deposit);
                    }
                    bankListener.put(p.getUniqueId().toString(), deposit);
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(bankerName+ChatColor.GRAY+ ": please enter deposit amount");
                    break;
            }

        }
    }





}
