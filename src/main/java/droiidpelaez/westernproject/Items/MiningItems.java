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
        lore.add(ChatColor.GRAY+"Refine before sale");
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
        iMeta.setDisplayName(ChatColor.DARK_GREEN+"Geode");
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
        lore.add(ChatColor.GRAY+"Refine before sale");
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
        lore.add(ChatColor.GRAY+"Refine before sale");
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
        lore.add(ChatColor.GRAY+"Refine before sale");
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
    public ItemStack getAmethyst()
    {
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Amethyst Bud");
        lore.add(ChatColor.GRAY+"A deep purple color");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getRiverDiamond()
    {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"River Diamond");
        lore.add(ChatColor.GRAY+"The root of many wars...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getCecilEmerald()
    {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Cecil's Emerald");
        lore.add(ChatColor.GRAY+"An old king was fond of these");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getSmallBones()
    {
        ItemStack item = new ItemStack(Material.PUMPKIN_SEEDS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Small Bones");
        lore.add(ChatColor.GRAY+"Maybe a small critter...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getLargeBone()
    {
        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Large bone");
        lore.add(ChatColor.GRAY+"Not even the whole thing...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getIronScrap()
    {
        ItemStack item = new ItemStack(Material.BEETROOT_SEEDS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Iron slag");
        lore.add(ChatColor.GRAY+"No use for this");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getRubble()
    {
        ItemStack item = new ItemStack(Material.BROWN_DYE);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Rubble");
        lore.add(ChatColor.GRAY+"No use for this");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
}
