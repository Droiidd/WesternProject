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
    public static ItemStack getSplint(){
        ItemStack splint = new ItemStack(Material.STICK);
        ItemMeta sMeta = splint.getItemMeta();
        ArrayList<String> sLore = new ArrayList<>();
        sLore.add("Use to heal broken legs");
        sMeta.setDisplayName(ChatColor.GRAY+ "Splint");
        sMeta.setLore(sLore);
        splint.setItemMeta(sMeta);
        return splint;


    }


}
