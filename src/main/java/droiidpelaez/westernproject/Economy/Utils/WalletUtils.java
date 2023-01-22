package droiidpelaez.westernproject.Economy.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WalletUtils {
    private static HashMap<String, Double> walletList = new HashMap<>();


    public static HashMap<String, Double> getWallets(){return walletList;}
    public static void createWallet(String playerId){
        walletList.put(playerId, 0.0);
        //.sendMessage(ChatColor.GRAY+"Wallet: $"+ChatColor.GOLD+walletList.get(p.getUniqueId().toString()));
    }
    public static void updateBalance(Player p, Double revenue){
        if(walletList.containsKey(p.getUniqueId().toString())){
            p.sendMessage(walletList.get(p.getUniqueId().toString()).toString());
            walletList.replace(p.getUniqueId().toString(), revenue + walletList.get(p.getUniqueId().toString()));
        }
        else{ createWallet(p.getUniqueId().toString()); }
    }
    public static void setBalance(String playerId, Double amount){
        if(walletList.containsKey(playerId)){
            walletList.replace(playerId,amount);
        }
        walletList.put(playerId, amount);
    }
    public static void removeMoney(Player p, Double withdrawal){
        Double newBalance = walletList.get(p.getUniqueId().toString()) - withdrawal;
        walletList.replace(p.getUniqueId().toString(), newBalance);
        p.sendMessage(ChatColor.GREEN+"Remaining balance: "+walletList.get(p.getUniqueId().toString())+"g");
    }
    public static Boolean hasAccount(Player player){
        return walletList.containsKey(player.getUniqueId().toString());
    }
    public static Double getPlayerFunds(Player player){
        if(!hasAccount(player)){
            createWallet(player.getUniqueId().toString());
            return walletList.get(player.getUniqueId().toString());
        }
        return walletList.get(player.getUniqueId().toString());
    }



}
