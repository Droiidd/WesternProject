package droiidpelaez.westernproject.Items.Listeners;

import droiidpelaez.westernproject.Items.PotionItems;
import droiidpelaez.westernproject.Items.Utils.RecipeUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class UseRecipe implements Listener
{
    @EventHandler
    public void playerUseRecipe(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        PotionItems potionItems = new PotionItems();
        //>>>===--- GREEN THUMB BREW ---===<<<
        if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                potionItems.getGreenThumbBrewRecipe().getItemMeta().getDisplayName()
        )) {
            //Player right-clicked the recipe!
            RecipeUtils recipes = new RecipeUtils();
            p.openInventory(recipes.getGreenThumbBrewRecipe(p));
        }
        // >>>===--- MINERS DOUBLE SPADE BREW ---===<<<
        else if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                potionItems.getMinersDoubleSpadeBrewRecipe().getItemMeta().getDisplayName()
        )) {
            //Player right-clicked the recipe!
            RecipeUtils recipes = new RecipeUtils();
            p.openInventory(recipes.getMinersDoubleSpadeBrewRecipe(p));
        }
        // >>>===--- MINERS FRENZY ---===<<<
        else if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                potionItems.getMinersFrenzyBrewRecipe().getItemMeta().getDisplayName()
        )) {
            //Player right-clicked the recipe!
            RecipeUtils recipes = new RecipeUtils();
            p.openInventory(recipes.getMinersFrenzyRecipe(p));
        }
        // >>>===--- FERMENTED LIQUOR---===<<<
        else if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                potionItems.getFermentedLiquorRecipe().getItemMeta().getDisplayName()
        )) {
            //Player right-clicked the recipe!
            RecipeUtils recipes = new RecipeUtils();
            p.openInventory(recipes.getFermentedBrewRecipe(p));
        }
        //Player clicked another recipe?
    }

    @EventHandler
    public void recipeOpen(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        // Meant to cancel all click events inside the inventory menu (avoid free items)
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Green Thumb Brew Recipe")){
            e.setCancelled(true);
        }
        else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Fermented Liquor Recipe")){
            e.setCancelled(true);
        }
        else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Miner's Frenzy Brew Recipe")){
            e.setCancelled(true);
        }
        else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY+"Miner's Double Spade Brew Recipe")){
            e.setCancelled(true);
        }


    }

}
