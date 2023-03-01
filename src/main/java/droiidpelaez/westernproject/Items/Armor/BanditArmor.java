package droiidpelaez.westernproject.Items.Armor;

import jdk.jfr.internal.test.WhiteBox;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

public class BanditArmor
{
    public ItemStack farmHandBoots()
    {
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta bMeta = boots.getItemMeta();
        bMeta.setDisplayName(ChatColor.WHITE+"Farmhand Boots");
        ArrayList<String> bLore = new ArrayList<>();
        bLore.add(ChatColor.GRAY+ "Size too big");
        bMeta.setLore(bLore);
        boots.setItemMeta(bMeta);
        return boots;
    }
    public ItemStack farmHandChaps()
    {
        ItemStack armor = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta aMeta = (LeatherArmorMeta) armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.WHITE+"Farmhand Chaps");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Stained brown with mud");
        aMeta.setLore(lore);
        aMeta.setColor(Color.fromRGB(153,102,51));
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack farmHandCap()
    {
        ItemStack armor = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta aMeta = (LeatherArmorMeta) armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.WHITE+"Farmhand Hat");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Keeps the sun away");
        aMeta.setLore(lore);
        aMeta.setColor(Color.fromRGB(153,102,51));
        armor.setItemMeta(aMeta);
        return armor;
    }

    public ItemStack farmHandShirt()
    {
        ItemStack armor = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta aMeta = (LeatherArmorMeta) armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.WHITE+"Farmhand Shirt");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Stained brown from sweat");
        aMeta.setLore(lore);
        aMeta.setColor(Color.fromRGB(153,102,51));
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack huntsmenPants()
    {
        ItemStack armor = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.DARK_GREEN+"Huntsmen Trousers");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Mostly fur");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack huntsmenBoots()
    {
        ItemStack armor = new ItemStack(Material.IRON_BOOTS);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.DARK_GREEN+"Huntsmen Boots");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Steeled Leather");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack huntsmenJacket()
    {
        ItemStack armor = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.DARK_GREEN+"Huntsmen Jacket");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Made from beast hide");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack huntsmenHat()
    {
        ItemStack armor = new ItemStack(Material.IRON_HELMET);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.DARK_GREEN+"Huntsmen Hat");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Coonskin Cap!");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack frontierHat()
    {
        ItemStack armor = new ItemStack(Material.IRON_HELMET);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Frontier Hat");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Leather 20 gallon!");
        aMeta.setLore(lore);
        aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1,false);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack frontierJacket()
    {
        ItemStack armor = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Frontier Coat");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Embroidered with steel");
        aMeta.setLore(lore);
        aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1,false);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack frontierPants()
    {
        ItemStack armor = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Frontier Pants");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Plenty of pockets");
        aMeta.setLore(lore);
        aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1,false);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack frontierBoots()
    {
        ItemStack armor = new ItemStack(Material.IRON_BOOTS);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.LIGHT_PURPLE+"Frontier Boots");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Rusty spurs");
        aMeta.setLore(lore);
        aMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1,false);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack journeyMenBoots()
    {
        ItemStack armor = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.YELLOW+"Journeymen Boots");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Jingling spurs");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack journeyMenPants()
    {
        ItemStack armor = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.YELLOW+"Journeymen Pants");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"With knee pads!");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack journeyMenJacket()
    {
        ItemStack armor = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.YELLOW+"Journeymen Jacket");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Plated with armor");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
    public ItemStack journeyMenHelm()
    {
        ItemStack armor = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta aMeta =  armor.getItemMeta();
        aMeta.setDisplayName(ChatColor.YELLOW+"Journeymen Helm");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"Soul of every journeyman");
        aMeta.setLore(lore);
        armor.setItemMeta(aMeta);
        return armor;
    }
}
