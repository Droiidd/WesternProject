package droiidpelaez.westernproject.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sun.security.krb5.internal.ASRep;

import java.util.ArrayList;

public class MiningItems
{
    //unproc gold, iron, copper
    //unproc cluster gold, iron, copper
    //proc gold, iron, copper
    //Geode -> eventually a couple gems

    //UN REFINED ORES
    public ItemStack getUnRefinedGold()
    {
        ItemStack item = new ItemStack( Material.RAW_GOLD);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Unrefined Gold Ore");
        lore.add(ChatColor.GRAY+"Refine refinement before sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getUnRefinedIron()
    {
        ItemStack item = new ItemStack(Material.RAW_IRON);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Unrefined Iron Ore");
        lore.add(ChatColor.GRAY+"Refine before sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getUnRefinedCopper()
    {
        ItemStack item = new ItemStack(Material.RAW_COPPER);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Unrefined Copper Ore");
        lore.add(ChatColor.GRAY+"Refine before sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getGeode()
    {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.YELLOW+"Geode");
        lore.add(ChatColor.GRAY+"Right click with empty hand to break open!");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }


    // UN REFINED CLUSTERS
    public ItemStack getUnRefinedCopperCluster()
    {
        ItemStack item = new ItemStack(Material.RAW_COPPER_BLOCK);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Unrefined Copper Cluster");
        lore.add(ChatColor.GRAY+"Requires refinement before sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getUnRefinedGoldCluster()
    {
        ItemStack item = new ItemStack(Material.RAW_GOLD_BLOCK);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Unrefined Gold Cluster");
        lore.add(ChatColor.GRAY+"Requires refinement before sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getUnRefinedIronCluster()
    {
        ItemStack item = new ItemStack(Material.RAW_IRON_BLOCK);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Unrefined Iron Cluster");
        lore.add(ChatColor.GRAY+"Requires refinement before sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    // REFINED ORES
    public ItemStack getRefinedCopper()
    {
        ItemStack item = new ItemStack(Material.COPPER_INGOT);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.AQUA+"Refined Copper Ingot");
        lore.add(ChatColor.GRAY+"Bring to a smith worker for sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getRefinedGold()
    {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.AQUA+"Refined Gold Ingot");
        lore.add(ChatColor.GRAY+"Bring to a smith worker for sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getRefinedIron()
    {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.AQUA+"Refined Iron Ingot");
        lore.add(ChatColor.GRAY+"Bring to a smith worker for sale");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
}
