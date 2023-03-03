package droiidpelaez.westernproject.Items.Utils;

import droiidpelaez.westernproject.Items.ForageItems;
import droiidpelaez.westernproject.Items.PotionItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

public class RecipeUtils
{
    private PotionItems pItems = new PotionItems();
    private ForageItems fItems = new ForageItems();

    public Inventory getGreenThumbBrewRecipe(Player p)
    {
        Inventory recipe = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY+"Green Thumb Brew Recipe");
        ItemStack goldenGamble = fItems.getGoldenGamblePetal();
        ItemStack starPedal = fItems.getStarPetal();
        ItemStack fermentedLiquor = pItems.getFermentedLiquor();
        ItemStack greenThumb = pItems.getGreenThumbBrew();

        recipe.setItem(18,goldenGamble);
        recipe.setItem(10,starPedal);
        recipe.setItem(19,fermentedLiquor);
        recipe.setItem(15, greenThumb);
        return recipe;
    }
    public Inventory getFermentedBrewRecipe(Player p)
    {
        Inventory recipe = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY+"Fermented Liquor Recipe");
        ItemStack fermentedLiquor = pItems.getFermentedLiquor();
        ItemStack blaze = new ItemStack(Material.BLAZE_POWDER);

        recipe.setItem(10, blaze);

        recipe.setItem(15, fermentedLiquor);
        return recipe;
    }
    public Inventory getMinersFrenzyRecipe(Player p)
    {
        Inventory recipe = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY+"Miner's Frenzy Brew Recipe");
        ItemStack frenziedStems = fItems.getFrenziedStems();
        ItemStack molesBreath = fItems.getMolesBreathSpores();
        ItemStack fermentedLiquor = pItems.getFermentedLiquor();
        ItemStack minerFrenzy = pItems.getMiningFrenzyBrew(1);

        recipe.setItem(18,frenziedStems);
        recipe.setItem(10,fermentedLiquor);
        recipe.setItem(19,molesBreath);
        recipe.setItem(15, minerFrenzy);
        return recipe;
    }
    public Inventory getMinersDoubleSpadeBrewRecipe(Player p)
    {
        Inventory recipe = Bukkit.createInventory(p, 27, ChatColor.DARK_GRAY+"Miner's Double Spade Brew Recipe");
        ItemStack goldenGamble = fItems.getGoldenGamblePetal();
        ItemStack molesBreath = fItems.getMolesBreathSpores();
        ItemStack fermentedLiquor = pItems.getFermentedLiquor();
        ItemStack minersSpade = pItems.getMinersSpadeBrew();

        recipe.setItem(18,goldenGamble);
        recipe.setItem(10,fermentedLiquor);
        recipe.setItem(19,molesBreath);
        recipe.setItem(15, minersSpade);
        return recipe;
    }
}
