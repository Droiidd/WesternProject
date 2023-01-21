package droiidpelaez.westernproject;

import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.Economy.Commands.*;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import droiidpelaez.westernproject.CoreUtils.ConfigManager;
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
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Core extends JavaPlugin {
    private static HashMap<String, Double> bankList = BankAccountUtils.getBankList();
    private static HashMap<String, Double> walletList = WalletUtils.getWallets();
    private static HashMap<String, Boolean> bleedList = PlayerCore.getBleedList();
    private static HashMap<String, Double> pBountyList = PlayerCore.getPlayerBountyList();
    private static HashMap<String, Boolean> wantedList = PlayerCore.getWantedList();
    private static HashMap<String, Boolean> crippleList = PlayerCore.getCrippleList();
    private static HashMap<String,String> playerList = PlayerCore.getPlayerList();

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
        if(playerConfig.playerCFG.contains("bloodData") && playerConfig.playerCFG.contains("bountyData")
        && playerConfig.playerCFG.contains("crippleData") && playerConfig.playerCFG.contains("wantedData")){
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
        for(Map.Entry<String, Boolean> entry : crippleList.entrySet()){
            playerConfig.playerCFG.set("crippleData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Boolean> entry : wantedList.entrySet()){
            playerConfig.playerCFG.set("wantedData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Double> entry : pBountyList.entrySet()){
            playerConfig.playerCFG.set("bountyData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, String> entry : playerList.entrySet()){
            playerConfig.playerCFG.set("playerData."+entry.getKey(), entry.getValue());
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
            Boolean bloodStat = (Boolean) playerConfig.playerCFG.get("bloodData."+key);

            bleedList.put(key, bloodStat);
        });
        playerConfig.playerCFG.getConfigurationSection("crippleData").getKeys(false).forEach(key ->{
            Boolean crippleStat = (Boolean) playerConfig.playerCFG.get("crippleData."+key);

            crippleList.put(key, crippleStat);
        });
        playerConfig.playerCFG.getConfigurationSection("wantedData").getKeys(false).forEach(key ->{
            Boolean wantedStat = (Boolean) playerConfig.playerCFG.get("wantedData."+key);

            wantedList.put(key, wantedStat);
        });
        playerConfig.playerCFG.getConfigurationSection("bountyData").getKeys(false).forEach(key -> {
            Double bounty = (Double) playerConfig.playerCFG.get("bountyData."+key);
            System.out.println(ChatColor.GOLD+"PLAYER INFO: "+key +" "+ bounty);
            pBountyList.put(key, bounty);
        });
        playerConfig.playerCFG.getConfigurationSection("playerData").getKeys(false).forEach(key -> {
            String playerId = (String) playerConfig.playerCFG.get("playerData."+key);
            System.out.println(ChatColor.GOLD+"PLAYER INFO: "+key +" "+ playerId);
            playerList.put(key, playerId);
        });

        for (String playerId : playerList.values()) {
            Player player = GlobalUtils.getPlayerFromString(playerId);
            if(player == null){
                System.out.println(ChatColor.LIGHT_PURPLE+"EROEIR");
            }
            PlayerCore pCore = new PlayerCore(player,bleedList.get(playerId),crippleList.get(playerId),wantedList.get(playerId),pBountyList.get(playerId));
            System.out.printf(ChatColor.LIGHT_PURPLE+"PLAYER LOADED!");
        }
    }
    public void restoreFile(){
        walletConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) walletConfig.playerCFG.get("data."+key);;
            WalletUtils.setBalance(key, account);
        });
        System.out.println("PLayers loaded.");
        bankConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) bankConfig.playerCFG.get("data."+key);
            BankAccountUtils.setBalance(key, account);
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







