package droiidpelaez.westernproject.Economy.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GoldUtils {

    public static ItemStack getGoldItem(Double amount){
        //Create a new item based off a gold nugget
        ItemStack gold = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta goldMeta = gold.getItemMeta();
        //Changing its name and description
        goldMeta.setDisplayName(ChatColor.LIGHT_PURPLE+ ""+amount+" g");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"A solid gold coin.");
        goldMeta.setLore(lore);
        gold.setItemMeta(goldMeta);
        //return the item
        return gold;
    }

    public static ItemStack getNormNugget(){
        ItemStack gold = new ItemStack(Material.GOLD_NUGGET);
        return gold;
    }

}