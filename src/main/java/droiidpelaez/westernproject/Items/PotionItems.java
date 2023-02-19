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
    public ItemStack getMinersSpadeBrew()
    {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta iMeta = (PotionMeta) item.getItemMeta();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Miners spade brew");
        iMeta.setColor(Color.PURPLE);
        iMeta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 600 , 0), true);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getGreenThumbBrew()
    {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta iMeta = (PotionMeta) item.getItemMeta();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Green Thumb brew");
        iMeta.setColor(Color.GREEN);
        iMeta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 600 , 1), true);
        item.setItemMeta(iMeta);
        return item;
    }

    public ItemStack getFermentedLiquor()
    {
        ItemStack item = new ItemStack(Material.HONEY_BOTTLE);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Fermented Liquor");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY+"Used to make helpful brews...");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }


}