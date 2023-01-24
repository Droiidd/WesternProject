package droiidpelaez.westernproject.Economy;

import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.CoreUtils.ScoreboardUtils;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Wallet {
    private static HashMap<String, Double> walletList = new HashMap<>();


    public static HashMap<String, Double> getWallets(){return walletList;}
    public static void createWallet(String playerId){
        walletList.put(playerId, 0.0);
        //GlobalUtils.loadPidScoreboard(playerId);

    }
    public static void updateBalance(Player p, Double revenue){
        if(walletList.containsKey(p.getUniqueId().toString())){
            walletList.replace(p.getUniqueId().toString(), revenue + walletList.get(p.getUniqueId().toString()));
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadBanditScoreboard(p);
        }
        else{ createWallet(p.getUniqueId().toString()); }
    }
    public static void setBalance(String playerId, Double amount){
        if(walletList.containsKey(playerId)){
            walletList.replace(playerId,amount);
            //GlobalUtils.loadPidScoreboard(playerId);

        }
        walletList.put(playerId, amount);
        //GlobalUtils.loadPidScoreboard(playerId);
    }
    public static void removeMoney(Player p, Double withdrawal){
        Double newBalance = walletList.get(p.getUniqueId().toString()) - withdrawal;
        walletList.replace(p.getUniqueId().toString(), newBalance);
        ScoreboardUtils sb = new ScoreboardUtils();
        sb.loadBanditScoreboard(p);
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
