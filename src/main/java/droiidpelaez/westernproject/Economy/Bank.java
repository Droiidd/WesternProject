package droiidpelaez.westernproject.Economy;

import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.ChatColor;
import org.bukkit.block.data.type.Wall;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Bank
{
    private static HashMap<String, Double> bankList = new HashMap<>();
    private static HashMap<String, Bank> playerBankList = new HashMap<>();
    private Double accountBalance;

    public Bank(String playerId)
    {
        playerBankList.put(playerId, this);
    }
    public static Bank getPlayerBank(Player p)
    {
        return playerBankList.get(p.getUniqueId().toString());
    }
    // ==== CREATE ====
    public void createBankAccount(Player p)
    {
        Double startBal = 500.00;
        bankList.put(p.getUniqueId().toString(), startBal);
        p.sendMessage(ChatColor.GREEN + "Player bank created.");
        p.sendMessage(ChatColor.GRAY + p.getDisplayName() + "'s current balance: $" + startBal);
        GlobalUtils.loadPidScoreboard(p.getUniqueId().toString());
    }
    public static HashMap<String, Double> getBankList()
    {
        return bankList;
    }
    public void addFunds(Player p, Double revenue)
    {
        if (bankList.containsKey(p.getUniqueId().toString())) {
            bankList.replace(p.getUniqueId().toString(), revenue + bankList.get(p.getUniqueId().toString()));
            p.sendMessage(ChatColor.GRAY + "$" + revenue + " has been added to your account!");
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(p);
        } else {
            createBankAccount(p);
        }
    }
    public void setBalance(String playerId, Double amount)
    {
        if(bankList.containsKey(playerId)){
            bankList.replace(playerId,amount);
            //GlobalUtils.loadPidScoreboard(playerId);
        }
        bankList.put(playerId, amount);
       // GlobalUtils.loadPidScoreboard(playerId);
    }
    public void removeFunds(Player p, Double withdrawal)
    {
        if (!bankList.containsKey(p.getUniqueId().toString())) {
            createBankAccount(p);
        }
        Double newBalance = bankList.get(p.getUniqueId().toString()) - withdrawal;
        if(newBalance >0){
            bankList.replace(p.getUniqueId().toString(), newBalance);
            //p.sendMessage(ChatColor.GRAY + "Remaining balance: $" + bankList.get(p.getUniqueId().toString()));
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(p);
        }
    }
    public void npcWithdraw(Player p, Double withdrawal)
    {
        Wallet wallet = Wallet.getPlayerWallet(p);
        if (!bankList.containsKey(p.getUniqueId().toString())) {
            createBankAccount(p);
        }
        Double newBalance = bankList.get(p.getUniqueId().toString()) - withdrawal;
        if(newBalance >=0){
            bankList.replace(p.getUniqueId().toString(), newBalance);
            wallet.addFunds(p,withdrawal);
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(p);
        }
    }
    public Boolean hasAccount(Player p)
    {
        return bankList.containsKey(p.getUniqueId().toString());
    }
    public Double getPlayerFunds(Player p)
    {
        if (bankList.containsKey(p.getUniqueId().toString())) {
            return bankList.get(p.getUniqueId().toString());
        } else {
            createBankAccount(p);
            getPlayerFunds(p);
        }
        return -1.0;
    }
    public void deleteAccount(Player p)
    {
        bankList.remove(p.getUniqueId().toString());
    }
    public void deleteAllAct()
    {
        bankList = new HashMap<String, Double>();
    }
}

