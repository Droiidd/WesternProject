package droiidpelaez.westernproject.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DrugItems
{
    public ItemStack getWildgrass()
    {
        ItemStack item = new ItemStack(Material.KELP);
        ItemMeta iMeta= item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.DARK_RED+"Wildgrass");
        lore.add(ChatColor.DARK_GRAY+"The gonja herb");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getJackoFruit()
    {
        ItemStack item = new ItemStack(Material.CHORUS_FRUIT);
        ItemMeta iMeta= item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.DARK_RED+"Jack 'O Fruit");
        lore.add(ChatColor.DARK_GRAY+"Some serious alien shit");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getProcJackoFruit()
    {
        ItemStack item = new ItemStack(Material.SUGAR);
        ItemMeta iMeta= item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.DARK_RED+"Processed Jack 'O Fruit");
        lore.add(ChatColor.DARK_GRAY+"Here we go again");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getProcWildgrass()
    {
        ItemStack item = new ItemStack(Material.SCUTE);
        ItemMeta iMeta= item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.DARK_RED+"Processed wildgrass");
        lore.add(ChatColor.DARK_GRAY+"The gonja herb, but for real");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getSuperdrug()
    {
        ItemStack item = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta iMeta= item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.DARK_RED+"Super drug");
        lore.add(ChatColor.DARK_GRAY+"We finally found it");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }




}
