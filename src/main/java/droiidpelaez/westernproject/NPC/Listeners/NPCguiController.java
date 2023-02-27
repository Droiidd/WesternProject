package droiidpelaez.westernproject.NPC.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.Items.Utils.GeodeController;
import droiidpelaez.westernproject.Items.MiningItems;
import droiidpelaez.westernproject.Items.Tools.Tools;
import droiidpelaez.westernproject.UtilCore.CoreGlobalUtils;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class NPCguiController implements Listener
{
    private HashMap<String, Boolean> chatListener = new HashMap<>();
    private HashMap<String, Integer> bankListener = new HashMap<>();
    private String geologistName = ChatColor.BLUE+""+ ChatColor.BOLD+"Geologist";
    private String bankerName = ChatColor.GOLD+""+ChatColor.BOLD+"Banker";
    private String conductorName = ChatColor.GRAY+""+ChatColor.BOLD+"Conductor";
    private Core plugin;
    public NPCguiController(Core plugin)
    {
        this.plugin = plugin;
    }


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
        //This handler is obnoxiously complicated and needs cleaning.
        Player p = e.getPlayer();
        Bank bank = Bank.getPlayerBank(p);
        Wallet wallet = Wallet.getPlayerWallet(p);
        //Check if player has EVER chatted with an NPC.
        if(chatListener.containsKey(p.getUniqueId().toString())){
            //Check if player RECENTLY chatted with NPC (check the list if it's true)
            if(chatListener.get(p.getUniqueId().toString())){
                e.setCancelled(true);
                //Player recently chatted with *any* NPC
                //Check player chat for number

                // ===--- BANK RELATED ---===
                Double amount = GlobalUtils.checkStrToDErrMsg(e.getMessage(), p);
                if(amount == -1){
                    //Player did not enter a valid number
                    p.sendMessage(bankerName+ChatColor.GRAY+ ": That's not right...");
                    chatListener.replace(p.getUniqueId().toString(), false);
                }
                //They entered a valid number
                else{
                    if(bankListener.containsKey(p.getUniqueId().toString())){
                        switch(bankListener.get(p.getUniqueId().toString())){
                            case 1:
                                //WITHDRAWAL
                                if(bank.getPlayerFunds(p) -amount >= 0.0){
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    p.sendMessage(bankerName+ChatColor.GRAY+": Withdrew "+ChatColor.GREEN+amount+"g");
                                    bank.npcWithdraw(p, amount);
                                }else{
                                    p.sendMessage(bankerName+ChatColor.GRAY+ ": Not enough funds.");
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    //remove all lists
                                }
                                break;

                            case 2:
                                // ===--- DEPOSIT ---===
                                p.sendMessage(""+wallet.getPlayerFunds(p)+"");
                                if(wallet.getPlayerFunds(p) - amount >= 0.0){
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    p.sendMessage(bankerName+ChatColor.GRAY+": Deposited "+ChatColor.GREEN+amount+"g");
                                    wallet.npcDeposit(p, amount);
                                }else{
                                    p.sendMessage(bankerName+ChatColor.GRAY+ ": Not enough funds.");
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                }
                                break;
                        }
                    }
                }
                // ===--- END OF BANK ---===
            }
           //player has not RECENTLY interacted with an NPC
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
        CoreGlobalUtils gUtils = new CoreGlobalUtils(plugin);
        // >>>===--- GEOLOGIST ---===<<<
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
                case FIREWORK_STAR:
                    //Break geode here
                    if(wallet.getPlayerFunds(p)>150.0){
                        ItemStack geode = miningItems.getGeode();
                        GeodeController geoController = new GeodeController();
                        GlobalUtils globalUtils = new GlobalUtils();
                        for(int j =0;j<64;j++){
                            //Checking if a player has geodes of any quantity
                            if(p.getInventory().contains(geode, j)){
                                p.getInventory().removeItem(geode);
                                wallet.removeMoney(p, 150.0);
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
        // >>>===--- CONDUCTOR ---===<<<
        else if(e.getView().getTitle().equalsIgnoreCase(conductorName+": Where we heading?")){
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()){
                case BARRIER:
                    p.closeInventory();
                    msgThanksForShopping(p, bankerName);
                    break;
                case NETHER_STAR:
                    if(wallet.getPlayerFunds(p) >= 250.0){ //250 is price to ride
                        p.closeInventory();

                        p.sendMessage(conductorName+": All aboard!!");
                        p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE,1,1);
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                //p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE,1,0);
                                p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE,1,1);
                            }
                        }, 1L);

                        //CORDS NEED CHANGING
                        Location santaFe = new Location(p.getWorld(), 1080, 90, -1953);
                        gUtils.fastTravelPlayer(p, santaFe);
                        wallet.removeMoney(p, 250.0);
                    }
                    break;
                case DEAD_BUSH:
                    //republic of texas
                    break;

            }

        }
    }





}
