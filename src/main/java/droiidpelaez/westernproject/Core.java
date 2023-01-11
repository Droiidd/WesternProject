package droiidpelaez.westernproject;

import droiidpelaez.westernproject.Economy.Commands.*;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import droiidpelaez.westernproject.Files.CustomConfig;
import droiidpelaez.westernproject.Teams.Commands.TeamCommands;
import droiidpelaez.westernproject.Teams.Listeners.OnPlayerChat;
import org.bukkit.block.data.type.Wall;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {
    private static HashMap<String, Double> bankList = BankAccountUtils.getBankList();
    private static HashMap<String, Double> walletList = WalletUtils.getWallets();
    private static CustomConfig bankAccounts = new CustomConfig("bankAccounts");
    private static CustomConfig wallets = new CustomConfig("wallets");



    @Override
    public void onEnable() {

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

        // === SAVING ===




        bankAccounts.setup();
        bankAccounts.getCustomFile().options().copyDefaults(true);
        bankAccounts.save();

        wallets.setup();
        wallets.getCustomFile().options().copyDefaults(true);
        wallets.save();



        if(bankAccounts.getCustomFile().contains("data")){
            restoreDoubleFile(bankAccounts, bankList);
        }
        if(wallets.getCustomFile().contains("data")){
            restoreDoubleFile(wallets, walletList);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(!walletList.isEmpty()){
            saveDoubleFile(wallets, walletList);
        }
        if(!bankList.isEmpty()){
            saveDoubleFile(bankAccounts,bankList);
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






}
