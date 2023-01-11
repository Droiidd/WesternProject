package droiidpelaez.westernproject;

import droiidpelaez.westernproject.Economy.Commands.*;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import droiidpelaez.westernproject.Files.ConfigManager;
import droiidpelaez.westernproject.Files.CustomConfig;
import droiidpelaez.westernproject.Teams.Commands.TeamCommands;
import droiidpelaez.westernproject.Teams.Listeners.OnPlayerChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.data.type.Wall;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {
    private static HashMap<String, Double> bankList = BankAccountUtils.getBankList();
    private static HashMap<String, Double> walletList = WalletUtils.getWallets();
    private static CustomConfig bankAccounts = new CustomConfig("bankAccounts");
    //private static CustomConfig wallets = new CustomConfig("wallets");
    private File walletFile;
    private FileConfiguration walletConfig;


    @Override
    public void onEnable() {
        loadConfigManager();

        // === COMMANDS ===
        getCommand("balance").setExecutor(new CheckBalance());
        getCommand("eco").setExecutor(new AdminCommands());
        getCommand("giveGold").setExecutor(new GiveGold());
        getCommand("drop").setExecutor(new DropGold());

        getCommand("wallet").setExecutor(new WalletCommands());
        getCommand("withdraw").setExecutor(new Withdraw());

        getCommand("team").setExecutor(new TeamCommands());


        // === EVENTS ===
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnGoldPickUp(), this);


        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);

        if(cfgn.playerCFG.contains("data")){
            System.out.println(ChatColor.RED+"DATA FOUND");
            restoreFile();
        }

        // === SAVING ===




        bankAccounts.setup();
        bankAccounts.getCustomFile().options().copyDefaults(true);
        bankAccounts.save();






        if(bankAccounts.getCustomFile().contains("data")){
            restoreDoubleFile(bankAccounts, bankList);
        }



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(!bankList.isEmpty()){
            saveDoubleFile(bankAccounts,bankList);
        }
        if(!walletList.isEmpty()){
            saveFile();
        }

    }

    public void saveDoubleFile( CustomConfig config, HashMap<String, Double> accountList){
        for(Map.Entry<String, Double> entry : accountList.entrySet()){
            config.getCustomFile().set("data."+entry.getKey(), entry.getValue());
        }
        config.save();

    }
    public void restoreDoubleFile( CustomConfig config,HashMap<String, Double> accountList){
        config.getCustomFile().getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) config.getCustomFile().get("data."+key);
            accountList.put(key, account);
        });
    }





    public void saveFile(){
        for(Map.Entry<String, Double> entry : walletList.entrySet()){
            cfgn.playerCFG.set("data."+entry.getKey(), entry.getValue());
        }
        cfgn.savePlayers();

    }

    public void restoreFile(){

        cfgn.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) cfgn.playerCFG.get("data."+key);;

            walletList.put(key, account);
        });
    }

    private ConfigManager cfgn;
    public void loadConfigManager(){
        cfgn = new ConfigManager();
        cfgn.setup();

    }


    }







