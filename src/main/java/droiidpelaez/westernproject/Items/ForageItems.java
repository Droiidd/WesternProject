package droiidpelaez.westernproject.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ForageItems
{
    public ItemStack getStarPetal()
    {
        ItemStack item = new ItemStack(Material.GLOW_INK_SAC);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.GREEN+"Star Petal");
        lore.add(ChatColor.DARK_GRAY+ "..Smell makes dreams come true..");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);

        return item;
    }
    public ItemStack getLifePetal()
    {
        ItemStack item = new ItemStack(Material.QUARTZ);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.GREEN+"Life Petal");
        lore.add(ChatColor.DARK_GRAY+ "..Ancient Medicines once used this..");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);

        return item;
    }
    public ItemStack getGoldenGamblePetal()
    {
        ItemStack item = new ItemStack(Material.HONEYCOMB);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.GREEN+"Golden Gamble Petal");
        lore.add(ChatColor.DARK_GRAY+ "..A symbol of fortune for ..");
        lore.add(ChatColor.DARK_GRAY+ "..many families of great wealth ..");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);

        return item;
    }
    public ItemStack getMolesBreathSpores()
    {
        ItemStack item = new ItemStack(Material.FROGSPAWN);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.GREEN+"'Moles Breath' Spores");
        lore.add(ChatColor.DARK_GRAY+ "..Some species of moles thrive off this..");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);

        return item;
    }
    public ItemStack getHeartFruit()
    {
        ItemStack item = new ItemStack(Material.SWEET_BERRIES);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.GREEN+"Heart Fruit");
        lore.add(ChatColor.DARK_GRAY+ "..Native to the deep woodland forests..");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);

        return item;
    }
    public ItemStack getFrenziedStems()
    {
        ItemStack item = new ItemStack(Material.FIRE_CORAL);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.GREEN+"Frenzied Stems");
        lore.add(ChatColor.DARK_GRAY+ "..Used on workers for productivity..");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);

        return item;
    }





}
