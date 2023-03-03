package droiidpelaez.westernproject.Items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class PotionItems
{
    public ItemStack getMiningFrenzyBrew(int quality)
    {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta iMeta = (PotionMeta) item.getItemMeta();
        iMeta.setColor(Color.ORANGE);
        iMeta.addCustomEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600 , quality), true);

        if(quality > 0){
            iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Miners Frenzy Brew +"+quality);
            item.setItemMeta(iMeta);
            return item;
        }
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Miners Frenzy Brew");
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getMinersSpadeBrew()
    {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta iMeta = (PotionMeta) item.getItemMeta();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Miners Double Spade Brew");
        iMeta.setColor(Color.MAROON);
        iMeta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 600 , 0), true);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getGreenThumbBrew()
    {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta iMeta = (PotionMeta) item.getItemMeta();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Green Thumb Brew");
        iMeta.setColor(Color.GREEN);
        iMeta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 600 , 1), true);
        item.setItemMeta(iMeta);
        return item;
    }

    public ItemStack getFermentedLiquor()
    {
        ItemStack item = new ItemStack(Material.HONEY_BOTTLE);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.DARK_GREEN+"Fermented Liquor");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY+"Used to make helpful brews...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getFermentedLiquorRecipe()
    {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.DARK_GREEN+"Fermented Liquor Recipe");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY+"Right click to read...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getGreenThumbBrewRecipe()
    {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.DARK_GREEN+"Green Thumb Brew Recipe");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY+"Right click to read...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getMinersFrenzyBrewRecipe()
    {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.DARK_GREEN+"Miner's Frenzy Brew Recipe");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY+"Right click to read...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getMinersDoubleSpadeBrewRecipe()
    {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.DARK_GREEN+"Miner's Double Spade Brew Recipe");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY+"Right click to read...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }



}
