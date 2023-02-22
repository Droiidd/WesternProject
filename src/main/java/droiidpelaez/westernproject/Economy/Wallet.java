package droiidpelaez.westernproject.Economy;

import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Wallet
{
    private static HashMap<String, Double> walletList = new HashMap<>();
    private static HashMap<String, Wallet> playerWalletList = new HashMap<>();
    private Double accountBalance;
    public Wallet(String playerId)
    {
        playerWalletList.put(playerId, this);
    }
    public static Wallet getPlayerWallet(Player p)
    {
        return playerWalletList.get(p.getUniqueId().toString());
    }
    public static HashMap<String, Double> getWallets()
    {
        return walletList;
    }
    public void createWallet(String playerId)
    {
        walletList.put(playerId, 0.0);
    }
    public void addFunds(Player p, Double revenue)
    {
        accountBalance += revenue;
        displayScoreboard(p);
        if(walletList.containsKey(p.getUniqueId().toString())){
            walletList.replace(p.getUniqueId().toString(), revenue + walletList.get(p.getUniqueId().toString()));
        }
        else{ createWallet(p.getUniqueId().toString()); }
    }
    public void setBalance(String playerId, Double amount)
    {
        accountBalance = amount;
        if(walletList.containsKey(playerId)){
            walletList.replace(playerId,amount);
        }
        walletList.put(playerId, amount);
    }
    public void removeMoney(Player p, Double withdrawal)
    {
        if(getPlayerFunds(p) >= withdrawal){
            Double newBalance = accountBalance - withdrawal;
            accountBalance -= withdrawal;
            walletList.replace(p.getUniqueId().toString(), newBalance);
            displayScoreboard(p);
        }
    }
    public Boolean hasAccount(Player player)
    {
        return walletList.containsKey(player.getUniqueId().toString());
    }
    public Double getPlayerFunds(Player player)
    {
        if(!hasAccount(player)){
            createWallet(player.getUniqueId().toString());
            return accountBalance;
        }
        return accountBalance;
    }
    public void displayScoreboard(Player p)
    {
        ScoreboardUtils sb = new ScoreboardUtils();
        sb.loadPlayerScoreboard(p);
    }
}
