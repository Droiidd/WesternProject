package droiidpelaez.westernproject;

import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Utils.AccountHandler;
import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.Items.ForageItems;
import droiidpelaez.westernproject.Items.PotionItems;
import droiidpelaez.westernproject.SafeZones.Utils.SafeZoneHandler;
import droiidpelaez.westernproject.UtilCore.CommandRegister;
import droiidpelaez.westernproject.UtilCore.ConfigManager;
import droiidpelaez.westernproject.PlayerCore.Utils.PlayerHandler;

import droiidpelaez.westernproject.Teams.Utils.TeamHandler;
import droiidpelaez.westernproject.UtilCore.EventRegister;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Core extends JavaPlugin
{
    private static HashMap<String, Double> bankList = Bank.getBankList();
    private static HashMap<String, Double> walletList = Wallet.getWallets();
    private ConfigManager walletConfig;
    private ConfigManager bankConfig;
    private ConfigManager playerConfig;
    private ConfigManager teamConfig;
    @Override
    public void onEnable()
    {
        System.out.println("HEY WE STARTED");
        //Instantiating all file read/write classes
        loadConfigManager();
        saveDefaultConfig();
        PlayerHandler playerSaver = new PlayerHandler(this);
        TeamHandler teamSaver = new TeamHandler(this);
        AccountHandler actSaver = new AccountHandler(this);
        // === COMMANDS ===
        CommandRegister cmdRegister = new CommandRegister(this);
        cmdRegister.regTeamCommands();
        cmdRegister.regEconomyCommands();
        cmdRegister.regMiscCommands();
        // === EVENTS ===
        EventRegister eventRegister = new EventRegister(this);
        eventRegister.regAllEvents();
        // === LOADS SAFE ZONE CORDS FROM CONFIG ===
        SafeZoneHandler handler = new SafeZoneHandler(this);
        handler.loadCords();
        spruceSaplingRecipe();
        // === SAVING ===
        if(walletConfig.playerCFG.contains("data") && bankConfig.playerCFG.contains("data")){
            actSaver.restoreAccounts(walletConfig, bankConfig);
        }
        if(playerSaver.checkPlayerFileData(playerConfig)){
           playerSaver.restorePlayerFile(playerConfig);
        }
        if(teamSaver.checkPlayerFileData(teamConfig)){
            System.out.println("LOADING TEAMS");
            teamSaver.loadTeams(teamConfig);
        }
    }
    public void spruceSaplingRecipe() {
        PotionItems potionItems = new PotionItems();
        ForageItems forageItems = new ForageItems();
        ItemStack goldenGamble = forageItems.getGoldenGamblePetal();
        ItemStack molesBreath = forageItems.getMolesBreathSpores();
        ItemStack starPetal = forageItems.getStarPetal();
        ItemStack frenziedStems = forageItems.getFrenziedStems();

        ShapelessRecipe minersDoubleSpadeBrewRecipe = new ShapelessRecipe(new NamespacedKey(this, "double_spade_potion"),potionItems.getMinersSpadeBrew()).
                addIngredient(goldenGamble.getType()).addIngredient(molesBreath.getType()).addIngredient(potionItems.getFermentedLiquor().getType());
        getServer().addRecipe(minersDoubleSpadeBrewRecipe);

        ShapelessRecipe fermentedLiquorRecipe = new ShapelessRecipe(new NamespacedKey(this, "fermented_liquor"),potionItems.getFermentedLiquor()).
                addIngredient(Material.BLAZE_POWDER);
        getServer().addRecipe(fermentedLiquorRecipe);

        ShapelessRecipe greenThumbBrewRecipe = new ShapelessRecipe(new NamespacedKey(this, "green_thumb_brew"),potionItems.getGreenThumbBrew()).
                addIngredient(starPetal.getType()).addIngredient(goldenGamble.getType()).addIngredient(potionItems.getFermentedLiquor().getType());
        getServer().addRecipe(greenThumbBrewRecipe);

        ShapelessRecipe miningFrenzyBrewRecipe = new ShapelessRecipe(new NamespacedKey(this, "mining_frenzy_brew"),potionItems.getMiningFrenzyBrew(0)).
                addIngredient(frenziedStems.getType()).addIngredient(molesBreath.getType()).addIngredient(potionItems.getFermentedLiquor().getType());
        getServer().addRecipe(miningFrenzyBrewRecipe);




    }

    @Override
    public void onDisable()
    {
        //Instantiating all file read/write classes
        PlayerHandler playerSaver = new PlayerHandler(this);
        TeamHandler teamSaver = new TeamHandler(this);
        AccountHandler actSaver = new AccountHandler(this);
        if(!bankList.isEmpty() && !walletList.isEmpty()){
            actSaver.saveAccounts(walletConfig, bankConfig);
        }
        if(teamSaver.checkPlayerMaps()){
            teamSaver.saveTeams(teamConfig);
        }
        if(playerSaver.checkPlayerMaps()){
            playerSaver.savePlayerFile(playerConfig);
        }
    }

    // === FILE PERSISTENT DELETION METHODS ===
    public void removeTeam(String teamName, String playerId)
    {
        if(teamConfig.playerCFG.contains("teamData."+teamName)){
            teamConfig.playerCFG.set("teamData."+teamName, null);
            teamConfig.savePlayers();
            System.out.println(ChatColor.AQUA+"DATA SAVED");
        }
        if(teamConfig.playerCFG.contains("playerData."+playerId)){
            teamConfig.playerCFG.set("playerData."+playerId, null);
            teamConfig.savePlayers();
            System.out.println(ChatColor.AQUA+"DATA SAVED");
        }
    }
    public void removeSheriff(String playerId)
    {
        System.out.println("SHERIFF ID"+playerId);
        if(playerConfig.playerCFG.contains("sheriffData."+playerId)){
            System.out.println("FOUND PLAYER ON FILE");
            playerConfig.playerCFG.set("sheriffData."+playerId, null);
            playerConfig.savePlayers();
            System.out.println("SHERIFF REMOVED");
        }

    }
    // === LOADS CONFIG MANAGERS ===
    public void loadConfigManager()
    {
        //wallets
        walletConfig = new ConfigManager();
        walletConfig.setup("walletInfo");
        //bank
        bankConfig = new ConfigManager();
        bankConfig.setup("bankInfo");
        //players
        playerConfig = new ConfigManager();
        playerConfig.setup("playerInfo");
        //teams
        teamConfig = new ConfigManager();
        teamConfig.setup("teamInfo");
        }
    }







