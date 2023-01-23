package droiidpelaez.westernproject;

import droiidpelaez.westernproject.Economy.Commands.*;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Economy.Utils.Bank;
import droiidpelaez.westernproject.Economy.Utils.Wallet;
import droiidpelaez.westernproject.CoreUtils.ConfigManager;
import droiidpelaez.westernproject.PlayerCore.Commands.CoreDisplay;
import droiidpelaez.westernproject.PlayerCore.Commands.ToggleScoreBoard;
import droiidpelaez.westernproject.PlayerCore.Listeners.WesternPlayerEvents;
import droiidpelaez.westernproject.PlayerCore.PlayerUtils;
import droiidpelaez.westernproject.Roles.Commands.RoleCommands;
import droiidpelaez.westernproject.Roles.Listeners.PlayerListeners;
import droiidpelaez.westernproject.Roles.RoleController;
import droiidpelaez.westernproject.Teams.Commands.TeamCommands;
import droiidpelaez.westernproject.Teams.Listeners.OnPlayerChat;
import droiidpelaez.westernproject.Teams.Utils.TeamUtils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {
    private static HashMap<String, Double> bankList = Bank.getBankList();
    private static HashMap<String, Double> walletList = Wallet.getWallets();
    private ConfigManager walletConfig;
    private ConfigManager bankConfig;
    private ConfigManager playerConfig;
    private ConfigManager teamConfig;
    private RoleController roleController;

    @Override
    public void onEnable() {
        System.out.println("HEY WE STARTED");
        loadConfigManager();
        saveDefaultConfig();
        PlayerUtils playerSaver = new PlayerUtils();
        TeamUtils teamSaver = new TeamUtils(this);

        //

        // === COMMANDS ===
        getCommand("balance").setExecutor(new CheckBalance());
        getCommand("eco").setExecutor(new AdminCommands());
        getCommand("giveGold").setExecutor(new GiveGold());
        getCommand("drop").setExecutor(new DropGold());

        getCommand("wallet").setExecutor(new CheckWallet());
        getCommand("withdraw").setExecutor(new Withdraw());
        getCommand("deposit").setExecutor(new Deposit());

        getCommand("team").setExecutor(new TeamCommands(this));

        getCommand("role").setExecutor(new RoleCommands(roleController));

        getCommand("toggleplayerinfo").setExecutor(new ToggleScoreBoard());
        getCommand("playerinfo").setExecutor(new CoreDisplay());
        System.out.println("COMMANDS REGISTERED");


        // === EVENTS ===
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnGoldPickUp(), this);

        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);

        getServer().getPluginManager().registerEvents(new WesternPlayerEvents(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListeners(roleController), this);

        // === SAVING ===
        if(walletConfig.playerCFG.contains("data") && bankConfig.playerCFG.contains("data")){
            System.out.println(ChatColor.RED+"DATA FOUND");
            restoreFile();
        }
        if(playerSaver.checkPlayerFileData(playerConfig)){
           playerSaver.restorePlayerFile(playerConfig);
        }
        if(teamSaver.checkPlayerFileData(teamConfig)){
            System.out.println("LOADING TEAMS");
            teamSaver.loadTeams(teamConfig);
        }
        roleController = new RoleController(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PlayerUtils playerSaver = new PlayerUtils();
        TeamUtils teamSaver = new TeamUtils(this);
        if(!bankList.isEmpty() && !walletList.isEmpty()){
            //saveDoubleFile(bankAccounts,bankList);
            saveFile();
        }
        if(teamSaver.checkPlayerMaps()){
            teamSaver.saveTeams(teamConfig);
        }
        if(playerSaver.checkPlayerMaps()){
            playerSaver.savePlayerFile(playerConfig);
        }

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
    public void restoreFile(){
        walletConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) walletConfig.playerCFG.get("data."+key);;
            Wallet.setBalance(key, account);
        });
        System.out.println("PLayers loaded.");
        bankConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) bankConfig.playerCFG.get("data."+key);
            Bank.setBalance(key, account);
        });

    }
    public void removeTeam(String teamName, String playerId){
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
    public void loadConfigManager(){
        //wallets
        walletConfig = new ConfigManager();
        walletConfig.setup("walletInfo");
        //teams
        bankConfig = new ConfigManager();
        bankConfig.setup("bankInfo");
        //players
        playerConfig = new ConfigManager();
        playerConfig.setup("playerInfo");

        teamConfig = new ConfigManager();
        teamConfig.setup("teamInfo");

    }

    }







