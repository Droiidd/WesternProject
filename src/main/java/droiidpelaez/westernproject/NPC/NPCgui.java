package droiidpelaez.westernproject.NPC;

import droiidpelaez.westernproject.Items.Tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class NPCgui
{
    public Inventory getGeologistShop(Player p)
    {
        Inventory test = Bukkit.createInventory(p, 27, ChatColor.BLUE+"Geologist - "+ ChatColor.GRAY+"For Sale:");

        ItemStack crackGeode = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta geodeMeta = crackGeode.getItemMeta();
        geodeMeta.setDisplayName(ChatColor.WHITE+"Crack a geode open!");
        ArrayList<String> geoLore = new ArrayList<>();
        geoLore.add("50.0 g");
        geodeMeta.setLore(geoLore);
        crackGeode.setItemMeta(geodeMeta);

        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.WHITE+"Leave");
        exit.setItemMeta(exitMeta);

        ItemStack pick = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta pickMeta = pick.getItemMeta();
        pickMeta.setDisplayName(ChatColor.WHITE+"Broken pick");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("200.0 g");
        pickMeta.setLore(lore);
        pick.setItemMeta(pickMeta);

        ItemStack pick2 = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta pickMeta2 = pick2.getItemMeta();
        pickMeta.setDisplayName(ChatColor.WHITE+"Old Miner's pick");
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add("800.0 g");
        pickMeta2.setLore(lore2);
        pick2.setItemMeta(pickMeta2);

        ItemStack pick3 = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickMeta3 = pick3.getItemMeta();
        pickMeta3.setDisplayName(ChatColor.LIGHT_PURPLE+"Explorer's pick");
        ArrayList<String> lore3 = new ArrayList<>();
        lore3.add("5 "+ChatColor.LIGHT_PURPLE+"River Diamonds"+ChatColor.BLUE+" 1600.0 g ");
        pickMeta3.setLore(lore3);
        pick3.setItemMeta(pickMeta3);

        test.setItem(0, exit);
        test.setItem(4, crackGeode);
        test.setItem(20, pick);
        test.setItem(22, pick2);
        test.setItem(24, pick3);
        return test;

    }





}
