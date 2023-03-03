package droiidpelaez.westernproject.NPC.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.Items.Armor.BanditArmor;
import droiidpelaez.westernproject.Items.Armor.SheriffArmor;
import droiidpelaez.westernproject.Items.FoodItems;
import droiidpelaez.westernproject.Items.HealthItems;
import droiidpelaez.westernproject.Items.PotionItems;
import droiidpelaez.westernproject.Items.Utils.GeodeController;
import droiidpelaez.westernproject.Items.MiningItems;
import droiidpelaez.westernproject.Items.Tools.Tools;
import droiidpelaez.westernproject.UtilCore.CoreGlobalUtils;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCguiController implements Listener {
    private HashMap<String, Boolean> chatListener = new HashMap<>();
    private HashMap<String, Integer> bankListener = new HashMap<>();
    private HashMap<String, String> surgeonListener = new HashMap<>();
    private HashMap<String, String> shopKeepListener = new HashMap<>();
    private String geologistName = ChatColor.BLUE + "" + ChatColor.BOLD + "Geologist";
    private String armorerName = ChatColor.BLUE + "" + ChatColor.BOLD + "Armorer";
    private String bankerName = ChatColor.GOLD + "" + ChatColor.BOLD + "Banker";
    private String conductorName = ChatColor.GRAY + "" + ChatColor.BOLD + "Conductor";
    private String illegalArmorerName = ChatColor.RED + "" + ChatColor.BOLD + "Illegal Armorer";
    private String sheriffArmorerName = ChatColor.DARK_AQUA+""+ ChatColor.BOLD+"Sheriff Armorer";
    private String surgeonName = ChatColor.BLUE+""+ChatColor.BOLD+"Surgeon";
    private String shopKeepName = ChatColor.BLUE+""+ChatColor.BOLD+"Shop Keep";
    private Core plugin;
    private boolean usingBanker;
    private boolean usingSurgeon;
    private boolean usingShopKeep;

    public NPCguiController(Core plugin) {
        this.plugin = plugin;
    }

    public void msgNotEnoughFunds(Player p, String npcName) {
        p.sendMessage(npcName + ": " + ChatColor.GRAY + "You do not have enough " + ChatColor.RED + "Funds!");
    }

    public void msgThanksForShopping(Player p, String npcName) {
        p.sendMessage(npcName + ": " + ChatColor.GRAY + "Please, come again");
    }

    public void sellPlayerItem(Player p, String npcName, ItemStack purchasedItem, Double cost) {
        Wallet wallet = Wallet.getPlayerWallet(p);
        if (wallet.getPlayerFunds(p) >= cost) {
            //Player can afford
            p.closeInventory();
            wallet.removeMoney(p, cost);
            p.getInventory().addItem(purchasedItem);
            msgThanksForShopping(p, npcName);
        } else {
            //Player cannot afford
            msgNotEnoughFunds(p, npcName);
        }
    }

    public void sellLargeAmount(Player p, String npcName, ItemStack purchasedItem, Double cost, Double amount)
    {
        Wallet wallet = Wallet.getPlayerWallet(p);
        cost = cost * amount;
        if (wallet.getPlayerFunds(p) >= cost) {
            //Player can afford
            //int total = amount.intValue();
            purchasedItem.setAmount(amount.intValue());
            p.getInventory().addItem(purchasedItem);
            msgThanksForShopping(p, npcName);
            wallet.removeMoney(p, cost);
        } else {
            //Player cannot afford
            msgNotEnoughFunds(p, npcName);
        }
    }

    @EventHandler
    public void chatSaleHandler(AsyncPlayerChatEvent e) {
        //This handler is obnoxiously complicated and needs cleaning.
        Player p = e.getPlayer();
        Bank bank = Bank.getPlayerBank(p);
        Wallet wallet = Wallet.getPlayerWallet(p);
        //Check if player has EVER chatted with an NPC.
        if (chatListener.containsKey(p.getUniqueId().toString())) {
            //Check if player RECENTLY chatted with NPC (check the list if it's true)
            if (chatListener.get(p.getUniqueId().toString())) {
                e.setCancelled(true);
                //Player recently chatted with *any* NPC
                //Check player chat for number

                // ===--- BANK RELATED ---===
                Double amount = GlobalUtils.checkStrToDErrMsg(e.getMessage(), p);
                if (amount == -1) {
                    //Player did not enter a valid number
                    p.sendMessage(bankerName + ChatColor.GRAY + ": That's not right...");
                    chatListener.replace(p.getUniqueId().toString(), false);
                }
                //They entered a valid number
                else {
                    // >>>===--- Bank Listener ---===<<<
                    if(usingBanker){
                        if (bankListener.containsKey(p.getUniqueId().toString())) {
                            switch (bankListener.get(p.getUniqueId().toString())) {
                                case 1:
                                    //WITHDRAWAL
                                    if (bank.getPlayerFunds(p) - amount >= 0.0) {
                                        chatListener.replace(p.getUniqueId().toString(), false);
                                        p.sendMessage(bankerName + ChatColor.GRAY + ": Withdrew " + ChatColor.GREEN + amount + "g");
                                        bank.npcWithdraw(p, amount);
                                    } else {
                                        p.sendMessage(bankerName + ChatColor.GRAY + ": Not enough funds.");
                                        chatListener.replace(p.getUniqueId().toString(), false);
                                        //remove all lists
                                    }
                                    usingBanker = false;
                                    p.sendMessage(""+usingBanker+"");
                                    break;

                                case 2:
                                    // ===--- DEPOSIT ---===
                                    p.sendMessage("" + wallet.getPlayerFunds(p) + "");
                                    if (wallet.getPlayerFunds(p) - amount >= 0.0) {
                                        chatListener.replace(p.getUniqueId().toString(), false);
                                        p.sendMessage(bankerName + ChatColor.GRAY + ": Deposited " + ChatColor.GREEN + amount + "g");
                                        wallet.npcDeposit(p, amount);
                                    } else {
                                        p.sendMessage(bankerName + ChatColor.GRAY + ": Not enough funds.");
                                        chatListener.replace(p.getUniqueId().toString(), false);
                                    }
                                    usingBanker = false;
                                    break;
                            }
                        }
                    }
                    // >>>===--- Surgeon Listener ---===<<<
                    else if(usingSurgeon){
                        if(surgeonListener.containsKey(p.getUniqueId().toString())){
                            p.sendMessage("contains player");
                            HealthItems meds = new HealthItems();
                            p.sendMessage(surgeonListener.get(p.getUniqueId().toString()));
                            switch (surgeonListener.get(p.getUniqueId().toString())){
                                case "splint":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingSurgeon = false;
                                    sellLargeAmount(p, surgeonName, meds.getSplint(), 50.0, amount);
                                    break;
                                case "bandage":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingSurgeon = false;
                                    sellLargeAmount(p, surgeonName, meds.getBandage(), 50.0, amount);
                                    break;
                                case "whiskey":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingSurgeon = false;
                                    sellLargeAmount(p, surgeonName, meds.getWhiskey(), 50.0, amount);
                                    break;
                                case "morphine":
                                    usingSurgeon = false;
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    sellLargeAmount(p, surgeonName, meds.getMorphine(), 50.0, amount);
                                    break;
                            }
                        }
                    }
                    // >>>===--- Shop Keep Listener ---===<<<
                    else if (usingShopKeep){
                        if(shopKeepListener.containsKey(p.getUniqueId().toString())){
                            FoodItems food = new FoodItems();
                            p.sendMessage(shopKeepListener.get(p.getUniqueId().toString()));
                            switch (shopKeepListener.get(p.getUniqueId().toString())){
                                case "brown":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingShopKeep = false;
                                    sellLargeAmount(p, shopKeepName, food.brownStew(), 50.0, amount);
                                    break;
                                case "chicken":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingShopKeep = false;
                                    sellLargeAmount(p, shopKeepName, food.cookedChicken(), 50.0, amount);
                                    break;
                                case "rabbit":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingShopKeep = false;
                                    sellLargeAmount(p, shopKeepName, food.cookedRabbit(), 50.0, amount);
                                    break;
                                case "rabbitstew":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingShopKeep = false;
                                    sellLargeAmount(p, shopKeepName, food.rabbitStew(), 50.0, amount);
                                    break;
                                case "potato":
                                    chatListener.replace(p.getUniqueId().toString(), false);
                                    usingShopKeep = false;
                                    sellLargeAmount(p, shopKeepName, food.charredPotato(), 50.0, amount);
                                    break;
                            }
                        }
                    }
                }

            }
            //player has not RECENTLY interacted with an NPC
        }
    }

    @EventHandler
    public void salesmenHandler(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Bank bank = Bank.getPlayerBank(p);
        Wallet wallet = Wallet.getPlayerWallet(p);
        Tools tools = new Tools();
        MiningItems miningItems = new MiningItems();
        CoreGlobalUtils gUtils = new CoreGlobalUtils(plugin);

        // >>>===--- GEOLOGIST ---===<<<
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Geologist - " + ChatColor.GRAY + "For Sale:")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case BARRIER:
                    msgThanksForShopping(p, geologistName);
                    p.closeInventory();
                    break;
                case DIAMOND_PICKAXE:
                    if (p.getInventory().contains(miningItems.getRiverDiamond(), 5)) {
                        if (wallet.getPlayerFunds(p) > 1600.0) {
                            //ACCPET USER TRANSACTION
                            for (int i = 0; i < 5; i++) {
                                p.getInventory().remove(miningItems.getRiverDiamond());
                            }
                            wallet.removeMoney(p, 1600.0);
                            //GIVE USER THEIR ITEMS
                            ItemStack explorerPick = tools.getExplorerPick();
                            p.getInventory().addItem(explorerPick);
                            p.closeInventory();
                            p.sendMessage(ChatColor.GREEN + "Forged " + ChatColor.WHITE + "Explorers Pick " + ChatColor.GREEN + "1600.0g");
                        } else {
                            msgNotEnoughFunds(p, geologistName);
                        }
                    } else {
                        p.sendMessage(ChatColor.BLUE + "Geologist: " + ChatColor.GRAY + "You do not have enough " + ChatColor.RED + "River Diamonds!");
                    }
                    break;
                case IRON_PICKAXE:
                    if (wallet.getPlayerFunds(p) >= 800.0) {
                        //Make sure they can afford
                        wallet.removeMoney(p, 800.0);

                        //Giver user purchased Item
                        ItemStack oldMinersPick = tools.getOldMinersPick();
                        p.getInventory().addItem(oldMinersPick);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE + "Geologist: " + ChatColor.GRAY + "Purchased " + ChatColor.WHITE + "Old Miner's Pick " + ChatColor.GOLD + "800.0g");
                    } else {
                        msgNotEnoughFunds(p, geologistName);
                    }
                    break;
                case STONE_PICKAXE:
                    //sell item
                    if (wallet.getPlayerFunds(p) >= 200.0) {
                        //Make sure they can afford
                        wallet.removeMoney(p, 200.0);

                        //Giver user purchased Item
                        ItemStack brokenPick = tools.getBrokenPick();
                        p.getInventory().addItem(brokenPick);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE + "Geologist: " + ChatColor.GRAY + "Purchased " + ChatColor.WHITE + "Broken Pick " + ChatColor.GOLD + "200.0g");
                    } else {
                        msgNotEnoughFunds(p, geologistName);
                    }
                    break;
                case FIREWORK_STAR:
                    //Break geode here
                    if (wallet.getPlayerFunds(p) > 150.0) {
                        ItemStack geode = miningItems.getGeode();
                        GeodeController geoController = new GeodeController();
                        GlobalUtils globalUtils = new GlobalUtils();
                        for (int j = 0; j < 64; j++) {
                            //Checking if a player has geodes of any quantity
                            if (p.getInventory().contains(geode, j)) {
                                p.getInventory().removeItem(geode);
                                wallet.removeMoney(p, 150.0);
                                ArrayList<ItemStack> geodeContents = geoController.openGeode();
                                for (int i = 0; i < geodeContents.size(); i++) {
                                    p.getInventory().addItem(geodeContents.get(i));
                                }

                                p.closeInventory();
                                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_HIT, 1, 0);
                                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_BONE_BLOCK_BREAK, 1, 0);
                                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_COMPOSTER_FILL, 2, 0);
                                globalUtils.displayParticles(p.getLocation(), Particle.ELECTRIC_SPARK, Particle.CLOUD, 5);
                                p.sendMessage(ChatColor.BLUE + "Geologist: " + ChatColor.GRAY + "Cracked " + ChatColor.DARK_GREEN + "Geode");
                            }
                        }

                    } else {
                        msgNotEnoughFunds(p, geologistName);
                    }
            }

        }
        // >>>====--- BANKER ---===<<<
        else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GRAY + p.getName() + "'s Bank Account")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case BARRIER:
                    p.closeInventory();
                    msgThanksForShopping(p, bankerName);
                    break;
                case NETHERITE_BLOCK:
                    p.closeInventory();
                    Integer withdraw = 1;
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (bankListener.containsKey(p.getUniqueId().toString())) {
                        bankListener.replace(p.getUniqueId().toString(), withdraw);
                    }
                    bankListener.put(p.getUniqueId().toString(), withdraw);
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(bankerName + ChatColor.GRAY + ": please enter withdrawal amount");
                    usingBanker = true;
                    break;
                case EMERALD_BLOCK:
                    p.closeInventory();
                    Integer deposit = 2;
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (bankListener.containsKey(p.getUniqueId().toString())) {
                        bankListener.replace(p.getUniqueId().toString(), deposit);
                    }
                    bankListener.put(p.getUniqueId().toString(), deposit);
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(bankerName + ChatColor.GRAY + ": please enter deposit amount");
                    usingBanker = true;
                    break;
            }

        }
        // >>>===--- CONDUCTOR ---===<<<
        else if (e.getView().getTitle().equalsIgnoreCase(conductorName + ": Where we heading?")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case BARRIER:
                    p.closeInventory();
                    msgThanksForShopping(p, bankerName);
                    break;
                case NETHER_STAR:
                    if (wallet.getPlayerFunds(p) >= 250.0) { //250 is price to ride
                        p.closeInventory();

                        p.sendMessage(conductorName + ": All aboard!!");
                        p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                //p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE,1,0);
                                p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
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
        // >>>===--- ARMORER ---===<<<
        else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Armorer - " + ChatColor.GRAY + "For Sale:")) {
            e.setCancelled(true);
            BanditArmor armor = new BanditArmor();

            ItemStack fhBoots = armor.farmHandBoots();
            ItemStack fhPants = armor.farmHandChaps();
            ItemStack fhShirt = armor.farmHandShirt();
            ItemStack fhHat = armor.farmHandCap();

            ItemStack hmBoots = armor.huntsmenBoots();
            ItemStack hmPants = armor.huntsmenPants();
            ItemStack hmJacket = armor.huntsmenJacket();
            ItemStack hmHat = armor.huntsmenHat();

            ItemStack ftBoots = armor.frontierBoots();
            ItemStack ftPants = armor.frontierPants();
            ItemStack ftJacket = armor.frontierJacket();
            ItemStack ftHat = armor.frontierHat();
            Double itemPrice = GlobalUtils.itemPriceReader(e.getCurrentItem());
            if (itemPrice == -1) {
                p.sendMessage("price is fucked");
                //itemPrice is fucked
            } else {
                switch (e.getCurrentItem().getType()) {
                    case BARRIER:
                        p.closeInventory();
                        msgThanksForShopping(p, bankerName);
                        break;
                    case LEATHER_BOOTS:
                        sellPlayerItem(p, armorerName, fhBoots, itemPrice);
                        break;
                    case LEATHER_LEGGINGS:
                        sellPlayerItem(p, armorerName, fhPants, itemPrice);
                        break;
                    case LEATHER_CHESTPLATE:
                        sellPlayerItem(p, armorerName, fhShirt, itemPrice);
                        break;
                    case LEATHER_HELMET:
                        sellPlayerItem(p, armorerName, fhHat, itemPrice);
                        break;
                    case IRON_BOOTS:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, armorerName, ftBoots, itemPrice);
                        } else {
                            sellPlayerItem(p, armorerName, hmBoots, itemPrice);
                        }
                        break;
                    case IRON_LEGGINGS:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, armorerName, ftPants, itemPrice);
                        } else {
                            sellPlayerItem(p, armorerName, hmPants, itemPrice);
                        }
                        break;
                    case IRON_CHESTPLATE:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, armorerName, ftJacket, itemPrice);
                        } else {
                            sellPlayerItem(p, armorerName, hmJacket, itemPrice);
                        }
                        break;
                    case IRON_HELMET:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, armorerName, ftHat, itemPrice);
                        } else {
                            sellPlayerItem(p, armorerName, hmHat, itemPrice);
                        }
                        break;
                }
            }
        }
        // >>>===--- ILLEGAL ARMORER ---===<<<
        else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Illegal Armorer - " + ChatColor.GRAY + "For Sale:")) {
            e.setCancelled(true);
            BanditArmor armor = new BanditArmor();

            ItemStack ftBoots = armor.frontierBoots();
            ItemStack ftPants = armor.frontierPants();
            ItemStack ftJacket = armor.frontierJacket();
            ItemStack ftHat = armor.frontierHat();

            ItemStack jmBoots = armor.journeyMenBoots();
            ItemStack jmPants = armor.journeyMenPants();
            ItemStack jmJacket = armor.journeyMenJacket();
            ItemStack jmHat = armor.journeyMenHelm();

            Double itemPrice = GlobalUtils.itemPriceReader(e.getCurrentItem());
            if (itemPrice == -1) {
                p.sendMessage("price is fucked");
                //itemPrice is fucked
            } else {
                switch (e.getCurrentItem().getType()) {
                    case NETHERITE_BOOTS:
                        sellPlayerItem(p, illegalArmorerName, jmBoots, itemPrice);
                        break;
                    case NETHERITE_LEGGINGS:
                        sellPlayerItem(p, illegalArmorerName, jmPants, itemPrice);
                        break;
                    case NETHERITE_CHESTPLATE:
                        sellPlayerItem(p, illegalArmorerName, jmJacket, itemPrice);
                        break;
                    case NETHERITE_HELMET:
                        sellPlayerItem(p, illegalArmorerName, jmHat, itemPrice);
                        break;
                    case IRON_BOOTS:
                        sellPlayerItem(p, armorerName, ftBoots, itemPrice);
                        break;
                    case IRON_LEGGINGS:
                        sellPlayerItem(p, armorerName, ftPants, itemPrice);
                        break;
                    case IRON_CHESTPLATE:
                        sellPlayerItem(p, armorerName, ftJacket, itemPrice);
                        break;
                    case IRON_HELMET:
                        sellPlayerItem(p, armorerName, ftHat, itemPrice);
                        break;
                }
            }
        }
        // >>>===--- SHERIFF ARMORER ---===<<<
        else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+"Sheriff Armorer - "+ ChatColor.GRAY+"For Sale:")){
            e.setCancelled(true);
            SheriffArmor armor = new SheriffArmor();

            ItemStack sheriffBoots = armor.getSheriffBoots();
            ItemStack sheriffPants = armor.getSheriffPants();
            ItemStack sheriffJacket = armor.getSheriffJacket();
            ItemStack sheriffHat = armor.getSheriffHat();

            ItemStack deputyBoots = armor.getDeputyBoots();
            ItemStack deputyPants = armor.getDeputyPants();
            ItemStack deputyJacket = armor.getDeputyJacket();
            ItemStack deputyHat = armor.getDeputyHat();

            ItemStack marshallBoots = armor.getMarshallBoots();
            ItemStack marshallPants = armor.getMarshallPants();
            ItemStack marshallJacket = armor.getMarshallJacket();
            ItemStack marshallHat = armor.getMarshallHat();

            Double itemPrice = GlobalUtils.itemPriceReader(e.getCurrentItem());
            if (itemPrice == -1) {
                p.sendMessage("price is fucked");
                //itemPrice is fucked
            } else {
                switch (e.getCurrentItem().getType()) {
                    case DIAMOND_BOOTS:
                        sellPlayerItem(p, sheriffArmorerName, marshallBoots, itemPrice);
                        break;
                    case DIAMOND_LEGGINGS:
                        sellPlayerItem(p, sheriffArmorerName, marshallPants, itemPrice);
                        break;
                    case DIAMOND_CHESTPLATE:
                        sellPlayerItem(p, sheriffArmorerName, marshallJacket, itemPrice);
                        break;
                    case DIAMOND_HELMET:
                        sellPlayerItem(p, sheriffArmorerName, marshallHat, itemPrice);
                        break;
                    case CHAINMAIL_BOOTS:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, sheriffArmorerName, deputyBoots, itemPrice);
                        } else {
                            sellPlayerItem(p, sheriffArmorerName, sheriffBoots, itemPrice);
                        }
                        break;
                    case CHAINMAIL_LEGGINGS:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, sheriffArmorerName, deputyPants, itemPrice);
                        } else {
                            sellPlayerItem(p, sheriffArmorerName, sheriffPants, itemPrice);
                        }
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, sheriffArmorerName, deputyJacket, itemPrice);
                        } else {
                            sellPlayerItem(p, sheriffArmorerName, sheriffJacket, itemPrice);
                        }
                        break;
                    case CHAINMAIL_HELMET:
                        if (e.getCurrentItem().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                            sellPlayerItem(p, sheriffArmorerName, deputyHat, itemPrice);
                        } else {
                            sellPlayerItem(p, sheriffArmorerName, sheriffHat, itemPrice);
                        }
                        break;
                }
            }
        }
        // >>>===--- SURGEON ---===<<<
        else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE+"Surgeon - "+ChatColor.GRAY+"For Sale:")){
            e.setCancelled(true);
            HealthItems meds = new HealthItems();
            switch(e.getCurrentItem().getType()){
                case STICK:
                    p.closeInventory();
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (surgeonListener.containsKey(p.getUniqueId().toString())) {
                        surgeonListener.replace(p.getUniqueId().toString(), "splint");
                    }
                    surgeonListener.put(p.getUniqueId().toString(), "splint");
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(surgeonName + ChatColor.GRAY + ": How many would you like?");
                    usingSurgeon = true;
                    break;
                case PAPER:
                    p.closeInventory();
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (surgeonListener.containsKey(p.getUniqueId().toString())) {
                        surgeonListener.replace(p.getUniqueId().toString(), "bandage");
                    }
                    surgeonListener.put(p.getUniqueId().toString(), "bandage");
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(surgeonName + ChatColor.GRAY + ": How many would you like?");
                    usingSurgeon = true;
                    break;
                case POTION:
                    ItemStack potion = e.getCurrentItem();
                    PotionMeta pMeta = (PotionMeta) potion.getItemMeta();
                    String potionName = pMeta.getDisplayName();
                    p.closeInventory();
                    if(potionName.equalsIgnoreCase(meds.getMorphine().getItemMeta().getDisplayName())){
                        if (chatListener.containsKey(p.getUniqueId().toString())) {
                            chatListener.replace(p.getUniqueId().toString(), true);
                        }
                        if (surgeonListener.containsKey(p.getUniqueId().toString())) {
                            surgeonListener.replace(p.getUniqueId().toString(), "morphine");
                        }
                        surgeonListener.put(p.getUniqueId().toString(), "morphine");
                        chatListener.put(p.getUniqueId().toString(), true);
                        p.sendMessage(surgeonName + ChatColor.GRAY + ": How many would you like?");
                    }
                    else if(potionName.equalsIgnoreCase(meds.getWhiskey().getItemMeta().getDisplayName())){
                        if (chatListener.containsKey(p.getUniqueId().toString())) {
                            chatListener.replace(p.getUniqueId().toString(), true);
                        }
                        if (surgeonListener.containsKey(p.getUniqueId().toString())) {
                            surgeonListener.replace(p.getUniqueId().toString(), "whiskey");
                        }
                        surgeonListener.put(p.getUniqueId().toString(), "whiskey");
                        chatListener.put(p.getUniqueId().toString(), true);
                        p.sendMessage(surgeonName + ChatColor.GRAY + ": How many would you like?");
                    }
                    usingSurgeon = true;
                    break;
            }
        }
        // >>>===--- SHOP KEEP ---===<<<
        else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE+"Shop Keeper - "+ChatColor.GRAY+"For Sale:")){
            e.setCancelled(true);
            FoodItems food = new FoodItems();
            PotionItems recipes = new PotionItems();
            switch (e.getCurrentItem().getType()){
                case PAPER:
                    switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())){
                        case "Fermented Liquor Recipe":
                            sellPlayerItem(p, shopKeepName, recipes.getFermentedLiquorRecipe(), 50.0);
                            break;
                        case "Green Thumb Brew Recipe":
                            sellPlayerItem(p, shopKeepName, recipes.getGreenThumbBrewRecipe(), 50.0);
                            break;
                        case "Miner's Frenzy Brew Recipe":
                            sellPlayerItem(p, shopKeepName, recipes.getMinersFrenzyBrewRecipe(), 50.0);
                            break;
                        case "Miner's Double Spade Brew Recipe":
                            sellPlayerItem(p, shopKeepName, recipes.getMinersDoubleSpadeBrewRecipe(), 50.0);
                            break;
                    }
                    break;
                case COOKED_CHICKEN:
                    p.closeInventory();
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (shopKeepListener.containsKey(p.getUniqueId().toString())) {
                        shopKeepListener.replace(p.getUniqueId().toString(), "chicken");
                    }
                    shopKeepListener.put(p.getUniqueId().toString(), "chicken");
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(shopKeepName + ChatColor.GRAY + ": How many would you like?");
                    usingShopKeep = true;
                    break;
                case COOKED_RABBIT:
                    p.closeInventory();
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (shopKeepListener.containsKey(p.getUniqueId().toString())) {
                        shopKeepListener.replace(p.getUniqueId().toString(), "rabbit");
                    }
                    shopKeepListener.put(p.getUniqueId().toString(), "rabbit");
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(shopKeepName + ChatColor.GRAY + ": How many would you like?");
                    usingShopKeep = true;
                    break;
                case MUSHROOM_STEW:
                    p.closeInventory();
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (shopKeepListener.containsKey(p.getUniqueId().toString())) {
                        shopKeepListener.replace(p.getUniqueId().toString(), "brown");
                    }
                    shopKeepListener.put(p.getUniqueId().toString(), "brown");
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(shopKeepName + ChatColor.GRAY + ": How many would you like?");
                    usingShopKeep = true;
                    break;
                case RABBIT_STEW:
                    p.closeInventory();
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (shopKeepListener.containsKey(p.getUniqueId().toString())) {
                        shopKeepListener.replace(p.getUniqueId().toString(), "rabbitstew");
                    }
                    shopKeepListener.put(p.getUniqueId().toString(), "rabbitstew");
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(shopKeepName + ChatColor.GRAY + ": How many would you like?");
                    usingShopKeep = true;
                    break;
                case BAKED_POTATO:
                    p.closeInventory();
                    if (chatListener.containsKey(p.getUniqueId().toString())) {
                        chatListener.replace(p.getUniqueId().toString(), true);
                    }
                    if (shopKeepListener.containsKey(p.getUniqueId().toString())) {
                        shopKeepListener.replace(p.getUniqueId().toString(), "potato");
                    }
                    shopKeepListener.put(p.getUniqueId().toString(), "potato");
                    chatListener.put(p.getUniqueId().toString(), true);
                    p.sendMessage(shopKeepName + ChatColor.GRAY + ": How many would you like?");
                    usingShopKeep = true;
                    break;

            }

        }
    }
}
