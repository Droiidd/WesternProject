package droiidpelaez.westernproject.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FoodItems
{
    public ItemStack brownStew()
    {
        ItemStack food = new ItemStack(Material.MUSHROOM_STEW);
        ItemMeta foodMeta = food.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        foodMeta.setDisplayName(ChatColor.WHITE+"Brown Stew");
        lore.add(ChatColor.GRAY+"What's in this..?");
        foodMeta.setLore(lore);
        food.setItemMeta(foodMeta);
        return food;
    }
    public ItemStack rabbitStew()
    {
        ItemStack food = new ItemStack(Material.RABBIT_STEW);
        ItemMeta foodMeta = food.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        foodMeta.setDisplayName(ChatColor.WHITE+"Rabbit Stew");
        lore.add(ChatColor.GRAY+"Delicious with bread");
        foodMeta.setLore(lore);
        food.setItemMeta(foodMeta);
        return food;
    }
    public ItemStack charredPotato()
    {
        ItemStack food = new ItemStack(Material.BAKED_POTATO);
        ItemMeta foodMeta = food.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        foodMeta.setDisplayName(ChatColor.WHITE+"Charred Potato");
        lore.add(ChatColor.GRAY+"Cooked on the coals");
        foodMeta.setLore(lore);
        food.setItemMeta(foodMeta);
        return food;
    }
    public ItemStack cookedSalmon()
    {
        ItemStack food = new ItemStack(Material.COOKED_SALMON);
        ItemMeta foodMeta = food.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        foodMeta.setDisplayName(ChatColor.WHITE+"Cooked Salmon");
        lore.add(ChatColor.GRAY+"Caught in the highlands");
        foodMeta.setLore(lore);
        food.setItemMeta(foodMeta);
        return food;
    }
    public ItemStack cookedRabbit()
    {
        ItemStack food = new ItemStack(Material.COOKED_RABBIT);
        ItemMeta foodMeta = food.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        foodMeta.setDisplayName(ChatColor.WHITE+"Cooked Rabbit");
        lore.add(ChatColor.GRAY+"Find these near slough creek");
        foodMeta.setLore(lore);
        food.setItemMeta(foodMeta);
        return food;
    }
    public ItemStack cookedChicken()
    {
        ItemStack food = new ItemStack(Material.COOKED_CHICKEN);
        ItemMeta foodMeta = food.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        foodMeta.setDisplayName(ChatColor.WHITE+"Cooked Chicken");
        lore.add(ChatColor.GRAY+"Find these near the triple crown");
        foodMeta.setLore(lore);
        food.setItemMeta(foodMeta);
        return food;
    }

}
