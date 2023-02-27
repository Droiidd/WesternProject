package droiidpelaez.westernproject.Economy.Utils;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.UtilCore.ConfigManager;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class AccountHandler
{
    private HashMap<String, Double> walletList = Wallet.getWallets();
    private HashMap<String, Double> bankList = Bank.getBankList();
    private Core plugin;

    public AccountHandler(Core plugin)
    {
        this.plugin = plugin;
    }
    public void saveAccounts(ConfigManager walletConfig, ConfigManager bankConfig)
    {
        for(Map.Entry<String, Double> entry : walletList.entrySet()){
            walletConfig.playerCFG.set("data."+entry.getKey(), entry.getValue());
        }
        walletConfig.savePlayers();
        for(Map.Entry<String, Double> entry : bankList.entrySet()){
            bankConfig.playerCFG.set("data."+entry.getKey(), entry.getValue());
        }
        bankConfig.savePlayers();
    }
    /**
     * Reads through each line of the config file, reading the data back onto their hashmaps
     * At the end, it recreates the java object.
     * @param walletConfig, bankConfig
     */
    public void restoreAccounts(ConfigManager walletConfig, ConfigManager bankConfig)
    {
        System.out.println(ChatColor.DARK_GREEN+"Loading wallet funds");
        walletConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) walletConfig.playerCFG.get("data."+key);;
            Wallet wallet = new Wallet(key);
            wallet.setBalance(key, account);
        });
        System.out.println(ChatColor.DARK_GREEN+"Wallets loaded");
        System.out.println(ChatColor.DARK_GREEN+"Loading bank funds");
        bankConfig.playerCFG.getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) bankConfig.playerCFG.get("data."+key);
            Bank bank = new Bank(key);
            bank.setBalance(key, account);
        });
        System.out.println(ChatColor.DARK_GREEN+"Bank accounts loaded");

    }

}
