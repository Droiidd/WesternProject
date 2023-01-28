package droiidpelaez.westernproject.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HealthItems {

    public static ItemStack getBandage(){
        ItemStack bandage = new ItemStack(Material.PAPER);
        ItemMeta bMeta = bandage.getItemMeta();
        ArrayList<String> bLore = new ArrayList<>();
        bLore.add("Use this item to stop bleeding");
        bMeta.setLore(bLore);
        bMeta.setDisplayName(ChatColor.GRAY+"Bandage");
        bandage.setItemMeta(bMeta);
        return bandage;

    }


}
