package droiidpelaez.westernproject.NPC;

import droiidpelaez.westernproject.Items.Armor.BanditArmor;
import droiidpelaez.westernproject.Items.Tools.Tools;
import jdk.internal.foreign.ArenaAllocator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class NPCgui
{
    private String conductorName = ChatColor.GRAY+""+ChatColor.BOLD+"Conductor";
    public ItemStack getExitButton()
    {
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.WHITE+"Leave");
        exit.setItemMeta(exitMeta);
        return exit;
    }
    public ItemStack giveItemPrice(ItemStack item, double price)
    {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+""+price+"g");


        return newItem;
    }
    public Inventory getArmorer(Player p)
    {
        Inventory shop = Bukkit.createInventory(p, 27, ChatColor.BLUE+"Armorer - "+ChatColor.GRAY+"For Sale:");
        ItemStack exit = getExitButton();
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
        ItemMeta fhBootMeta = fhBoots.getItemMeta();
        fhBootMeta.





        shop.setItem(0, exit);
        return shop;
    }
    public Inventory getGeologistShop(Player p)
    {
        Inventory geoGui = Bukkit.createInventory(p, 27, ChatColor.BLUE+"Geologist - "+ ChatColor.GRAY+"For Sale:");
        ItemStack exit = getExitButton();

        ItemStack crackGeode = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta geodeMeta = crackGeode.getItemMeta();
        geodeMeta.setDisplayName(ChatColor.WHITE+"Crack a geode open!");
        ArrayList<String> geoLore = new ArrayList<>();
        geoLore.add("150.0 g");
        geodeMeta.setLore(geoLore);
        crackGeode.setItemMeta(geodeMeta);

        ItemStack pick = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta pickMeta = pick.getItemMeta();
        pickMeta.setDisplayName(ChatColor.WHITE+"Broken pick");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("200.0 g");
        pickMeta.setLore(lore);
        pick.setItemMeta(pickMeta);

        ItemStack pick2 = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta pickMeta2 = pick2.getItemMeta();
        pickMeta.setDisplayName(ChatColor.WHITE+"Old Miner's pick");
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add("800.0 g");
        pickMeta2.setLore(lore2);
        pick2.setItemMeta(pickMeta2);

        ItemStack pick3 = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickMeta3 = pick3.getItemMeta();
        pickMeta3.setDisplayName(ChatColor.LIGHT_PURPLE+"Explorer's pick");
        ArrayList<String> lore3 = new ArrayList<>();
        lore3.add("5 "+ChatColor.LIGHT_PURPLE+"River Diamonds"+ChatColor.BLUE+" 1600.0 g ");
        pickMeta3.setLore(lore3);
        pick3.setItemMeta(pickMeta3);

        geoGui.setItem(0, exit);
        geoGui.setItem(4, crackGeode);
        geoGui.setItem(20, pick);
        geoGui.setItem(22, pick2);
        geoGui.setItem(24, pick3);
        // 0  1  2  3  4  5  6  7  8
        // 9 10 11 12 13 14 15 16 17
        //18 19 20 21 22 23 24 25 26
        return geoGui;

    }
    public Inventory getConductorGui(Player p)
    {
        Inventory trainGui = Bukkit.createInventory(p, 27,  conductorName+": Where we heading?");

        ItemStack exit = getExitButton();

        ItemStack santaFe = new ItemStack(Material.NETHER_STAR);
        ItemMeta sfMeta = santaFe.getItemMeta();
        ArrayList<String> sfLore = new ArrayList<>();
        sfMeta.setDisplayName(ChatColor.WHITE+"Train to Santa Fe");
        sfLore.add(ChatColor.GOLD+"250g");
        sfMeta.setLore(sfLore);
        santaFe.setItemMeta(sfMeta);

        ItemStack texas = new ItemStack(Material.DEAD_BUSH);
        ItemMeta texMeta = texas.getItemMeta();
        ArrayList<String> texLore = new ArrayList<>();
        texMeta.setDisplayName(ChatColor.WHITE+"Train to the Republic of Texas");
        texLore.add(ChatColor.GOLD+"250g");
        texMeta.setLore(texLore);
        texas.setItemMeta(texMeta);

        trainGui.setItem(0, exit);
        trainGui.setItem(11, texas);
        trainGui.setItem(15, santaFe);
        return trainGui;
    }
    public Inventory getBankerGui(Player p)
    {
        Inventory bankGui = Bukkit.createInventory(p, 27, ChatColor.GRAY+p.getName()+"'s Bank Account");

        ItemStack deposit = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta dMeta = deposit.getItemMeta();
        dMeta.setDisplayName(ChatColor.WHITE+"Make a deposit");
        deposit.setItemMeta(dMeta);

        ItemStack withdraw = new ItemStack(Material.NETHERITE_BLOCK);
        ItemMeta wMeta = withdraw.getItemMeta();
        wMeta.setDisplayName(ChatColor.WHITE+"Make a withdrawal");
        withdraw.setItemMeta(wMeta);

        ItemStack exit = getExitButton();

        bankGui.setItem(0, exit);
        bankGui.setItem(11, withdraw);
        bankGui.setItem(15, deposit);
        return bankGui;
    }





}
