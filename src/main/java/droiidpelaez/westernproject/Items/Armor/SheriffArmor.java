package droiidpelaez.westernproject.Items.Armor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SheriffArmor
{
    public ItemStack getSheriffBoots()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Sheriff's Boots");
        lore.add(ChatColor.GRAY+"Stock black boots");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getSheriffPants()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Sheriff's Pants");
        lore.add(ChatColor.GRAY+"Utility belt included");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getSheriffJacket()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Sheriff's Jacket");
        lore.add(ChatColor.GRAY+"Wrong people in chains");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getSheriffHat()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.WHITE+"Sheriff's Hat");
        lore.add(ChatColor.GRAY+"A true officer");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }

    public ItemStack getDeputyBoots()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Deputy's Boots");
        lore.add(ChatColor.GRAY+"Keep marching");
        iMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2 , false);
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getDeputyPants()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Deputy's Pants");
        lore.add(ChatColor.GRAY+"This towns too small");
        iMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2 , false);
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getDeputyJacket()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Deputy's Jacket");
        lore.add(ChatColor.GRAY+"Badge of honor");
        iMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2 , false);
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getDeputyHat()
    {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Deputy's Hat");
        lore.add(ChatColor.GRAY+"A true officer");
        iMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2 , false);
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getMarshallBoots()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.YELLOW+"Marshall's Boots");
        lore.add(ChatColor.GRAY+"Freshly shined");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getMarshallPants()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.YELLOW+"Marshall's Pants");
        lore.add(ChatColor.GRAY+"Built in heaters");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getMarshallJacket()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.YELLOW+"Marshall's Jacket");
        lore.add(ChatColor.GRAY+"Boys in blue!");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }
    public ItemStack getMarshallHat()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta iMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        iMeta.setDisplayName(ChatColor.YELLOW+"Marshall's Hat");
        lore.add(ChatColor.GRAY+"The true officer");
        iMeta.setLore(lore);
        item.setItemMeta(iMeta);
        return item;
    }

}
