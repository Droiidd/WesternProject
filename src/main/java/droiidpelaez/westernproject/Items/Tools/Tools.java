package droiidpelaez.westernproject.Items.Tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Tools
{
    public ItemStack getBrokenPick()
    {
        ItemStack pick = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta pickMeta = pick.getItemMeta();
        pickMeta.setDisplayName(ChatColor.WHITE+"Broken pick");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"An old pick, retired long ago");
        pickMeta.setLore(lore);
        pick.setItemMeta(pickMeta);
        return pick;
    }
    public ItemStack getOldMinersPick()
    {
        ItemStack pick = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta pickMeta = pick.getItemMeta();
        pickMeta.setDisplayName(ChatColor.WHITE+"Old Miner's Pick");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Some fool must've left it...");
        pickMeta.setLore(lore);
        pick.setItemMeta(pickMeta);
        return pick;
    }
    public ItemStack getExplorerPick()
    {
        ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickMeta = pick.getItemMeta();
        pickMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Explorer's Pick");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD+"Used to conquer the new frontier!");
        pickMeta.setLore(lore);
        pick.setItemMeta(pickMeta);
        return pick;
    }


}
