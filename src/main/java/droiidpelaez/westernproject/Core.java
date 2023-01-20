package droiidpelaez.westernproject;

import com.sun.org.apache.xpath.internal.operations.Bool;
import droiidpelaez.westernproject.Economy.Commands.*;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import droiidpelaez.westernproject.Files.ConfigManager;
import droiidpelaez.westernproject.Files.CustomConfig;
import droiidpelaez.westernproject.PlayerCore.Commands.CoreDisplay;
import droiidpelaez.westernproject.PlayerCore.Commands.ToggleScoreBoard;
import droiidpelaez.westernproject.PlayerCore.Listeners.OnPlayerJoinEvent;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Roles.Commands.RoleCommands;
import droiidpelaez.westernproject.Roles.Listeners.PlayerListeners;
import droiidpelaez.westernproject.Roles.RoleController;
import droiidpelaez.westernproject.Teams.Commands.TeamCommands;
import droiidpelaez.westernproject.Teams.Listeners.OnPlayerChat;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {
    private static HashMap<String, Double> bankList = BankAccountUtils.getBankList();
    private static HashMap<String, Double> walletList = WalletUtils.getWallets();
    private static HashMap<String, Boolean> bleedList = PlayerCore.getBleedList();
    private static HashMap<String, Double> pBountyList = PlayerCore.getPlayerBountyList();
    private static HashMap<String,String> playerUUID = PlayerCore.getPlayerUUID();
    private ConfigManager walletConfig;
    private ConfigManager bankConfig;
    private ConfigManager playerConfig;
    private RoleController roleController;
    @Override
    public void onEnable() {
        System.out.println("HEY WE STARTED");
        loadConfigManager();
        saveDefaultConfig();

        //

        // === COMMANDS ===
        getCommand("balance").setExecutor(new CheckBalance());
        getCommand("eco").setExecutor(new AdminCommands());
        getCommand("giveGold").setExecutor(new GiveGold());
        getCommand("drop").setExecutor(new DropGold());

        getCommand("wallet").setExecutor(new WalletCommands());
        getCommand("withdraw").setExecutor(new Withdraw());
        getCommand("deposit").setExecutor(new Deposit());

        getCommand("team").setExecutor(new TeamCommands());

        getCommand("role").setExecutor(new RoleCommands(roleController));

        getCommand("toggleplayerinfo").setExecutor(new ToggleScoreBoard());
        getCommand("playerinfo").setExecutor(new CoreDisplay());
        System.out.println("COMMANDS REGISTERED");


        // === EVENTS ===
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnGoldPickUp(), this);

        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);

        getServer().getPluginManager().registerEvents(new OnPlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerListeners(roleController), this);

        // === SAVING ===
        if(walletConfig.playerCFG.contains("data") && bankConfig.playerCFG.contains("data")){
            System.out.println(ChatColor.RED+"DATA FOUND");
            restoreFile();
        }
        if(playerConfig.playerCFG.contains("bloodData") && playerConfig.playerCFG.contains("bountyData")){
            System.out.println(ChatColor.RED+"DATA FOUND");
            restorePlayerFile();
        }
        roleController = new RoleController(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(!bankList.isEmpty() && !walletList.isEmpty()){
            //saveDoubleFile(bankAccounts,bankList);
            saveFile();
        }
        if(!bleedList.isEmpty() && !pBountyList.isEmpty()){
            savePlayerFile();
        }

    }
    public void savePlayerFile(){
        for(Map.Entry<String, Boolean> entry : bleedList.entrySet()){
            playerConfig.playerCFG.set("bloodData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Double> entry : pBountyList.entrySet()){
            playerConfig.playerCFG.set("bountyData."+entry.getKey(), entry.getValue());
        }
        playerConfig.savePlayers();
    }

    public void saveFile(){
        for(Map.Entry<String, Double> entry : walletList.entrySet()){
            walletConfig.playerCFG.set("data."+entry.getKey(), entry.getValue());
        }
        walletConfig.savePlayers();
        for(Map.Entry<String, Double> entry : bankList.entrySet()){
            bankConfig.playerCFG.set("data."+entry.getKey(), entry.getValue());
        }
        bankConfig.savePlayers();

    }
    public void restorePlayerFile(){
        playerConfig.playerCFG.getConfigurationSection("bloodData").getKeys(false).forEach(key ->{
            Boolean bloodStat = (Boolean) walletConfig.playerCFG.get("bloodData."+key);
            bleedList.put(key, bloodStat);
        });
        playerConfig.playerCFG.getConfigurationSection("bountyData").getKeys(false).forEach(key -> {
            Double bounty = (Double) playerConfig.playerCFG.get("bountyData."+key);
            pBountyList.put(key, bounty);
        });
    }
    public void restoreFile(){
        walletConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) walletConfig.playerCFG.get("data."+key);
            walletList.put(key, account);
        });
        bankConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) bankConfig.playerCFG.get("data."+key);
            bankList.put(key, account);
        });

    }
    public void loadConfigManager(){
        //wallets
        walletConfig = new ConfigManager();
        walletConfig.setup("playerWallet");
        //teams
        bankConfig = new ConfigManager();
        bankConfig.setup("playerBank");
        //players
        playerConfig = new ConfigManager();
        playerConfig.setup("playerStats");

    }
    }







