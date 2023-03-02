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

public class HealthItems {

    public ItemStack getBandage(){
        ItemStack bandage = new ItemStack(Material.PAPER);
        ItemMeta bMeta = bandage.getItemMeta();
        ArrayList<String> bLore = new ArrayList<>();
        bLore.add("Use this item to stop bleeding");
        bMeta.setLore(bLore);
        bMeta.setDisplayName(ChatColor.GRAY+"Bandage");
        bandage.setItemMeta(bMeta);
        return bandage;

    }
    public ItemStack getSplint(){
        ItemStack splint = new ItemStack(Material.STICK);
        ItemMeta sMeta = splint.getItemMeta();
        ArrayList<String> sLore = new ArrayList<>();
        sLore.add("Use to heal broken legs");
        sMeta.setDisplayName(ChatColor.GRAY+ "Splint");
        sMeta.setLore(sLore);
        splint.setItemMeta(sMeta);
        return splint;
    }
    public ItemStack getMorphine()
    {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta pMeta = (PotionMeta) potion.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        pMeta.setDisplayName(ChatColor.DARK_GREEN+"Morphine");
        lore.add(ChatColor.GRAY+"Won't give an arm back");
        pMeta.setLore(lore);
        pMeta.setColor(Color.fromRGB(20,151,163));
        pMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1, false), false);
        potion.setItemMeta(pMeta);
        return potion;
    }
    public ItemStack getWhiskey()
    {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta pMeta = (PotionMeta) potion.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        pMeta.setDisplayName(ChatColor.DARK_GREEN+"Whiskey");
        lore.add(ChatColor.GRAY+"Stops all the bullets");
        pMeta.setLore(lore);
        pMeta.setColor(Color.fromRGB(135,99,38));
        pMeta.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1, false), false);
        potion.setItemMeta(pMeta);
        return potion;
    }
}
