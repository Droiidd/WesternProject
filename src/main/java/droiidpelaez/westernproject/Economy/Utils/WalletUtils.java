package droiidpelaez.westernproject.Economy.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WalletUtils {
    private static HashMap<String, Double> walletList = new HashMap<>();

    public static HashMap<String, Double> getWallets(){return walletList;}

    public static void createWallet(Player p){
        walletList.put(p.getUniqueId().toString(), 0.0);
        p.sendMessage(ChatColor.GRAY+"Wallet: $"+ChatColor.GOLD+walletList.get(p.getUniqueId().toString()));
    }
    public static void updateBalance(Player p, Double revenue){
        if(walletList.containsKey(p.getUniqueId().toString()) == true){
            walletList.replace(p.getUniqueId().toString(), revenue + walletList.get(p.getUniqueId().toString()));
        }
        else{ createWallet(p); }
    }

    public static void removeMoney(Player p, Double withdrawal){
        Double newBalance = walletList.get(p.getUniqueId().toString()) - withdrawal;
        walletList.replace(p.getUniqueId().toString(), newBalance);
        p.sendMessage(ChatColor.GREEN+"Remaining balance: "+walletList.get(p.getUniqueId().toString())+"g");
    }



}
