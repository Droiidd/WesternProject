package droiidpelaez.westernproject.Economy;

import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Bank {
    private static HashMap<String, Double> bankList = new HashMap<>();

    // ==== CREATE ====
    public static void createBankAccount(Player p) {
        Double startBal = 500.00;
        bankList.put(p.getUniqueId().toString(), startBal);
        p.sendMessage(ChatColor.GREEN + "Player bank created.");
        p.sendMessage(ChatColor.GRAY + p.getDisplayName() + "'s current balance: $" + startBal);
        GlobalUtils.loadPidScoreboard(p.getUniqueId().toString());
    }

    public static HashMap<String, Double> getBankList() {
        return bankList;
    }
    public static void updateBalance(Player p, Double revenue) {
        if (bankList.containsKey(p.getUniqueId().toString())) {
            bankList.replace(p.getUniqueId().toString(), revenue + bankList.get(p.getUniqueId().toString()));
            p.sendMessage(ChatColor.GRAY + "$" + revenue + " has been added to your account!");
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(p);
        } else {
            createBankAccount(p);
        }
    }
    public static void setBalance(String playerId, Double amount){
        if(bankList.containsKey(playerId)){
            bankList.replace(playerId,amount);
            //GlobalUtils.loadPidScoreboard(playerId);
        }
        bankList.put(playerId, amount);
       // GlobalUtils.loadPidScoreboard(playerId);
    }
    public static void removeFunds(Player p, Double withdrawal) {
        if (!bankList.containsKey(p.getUniqueId().toString())) {
            createBankAccount(p);
        }
        Double newBalance = bankList.get(p.getUniqueId().toString()) - withdrawal;
        bankList.replace(p.getUniqueId().toString(), newBalance);
        p.sendMessage(ChatColor.GRAY + "Remaining balance: $" + bankList.get(p.getUniqueId().toString()));
        ScoreboardUtils sb = new ScoreboardUtils();
        sb.loadPlayerScoreboard(p);
    }
    public static Boolean hasAccount(Player p) {
        return bankList.containsKey(p.getUniqueId().toString());
    }
    public static Double getPlayerFunds(Player p) {
        if (bankList.containsKey(p.getUniqueId().toString())) {
            return bankList.get(p.getUniqueId().toString());
        } else {
            createBankAccount(p);
            getPlayerFunds(p);
        }
        return -1.0;
    }
    public static void deleteAccount(Player p) {
        bankList.remove(p.getUniqueId().toString());
    }
    public static void deleteAllAct() {
        bankList = new HashMap<String, Double>();
    }


}



    // ==== MISC ====
//    public static void displaySortedBanks(Player p){
//
//    }
//
//    public static ArrayList<Double> sort(ArrayList<Double> arr){
//        if(arr.size() <= 1){return arr;}
//
//        ArrayList<Double> left = new ArrayList<>();
//        ArrayList<Double> right = new ArrayList<>();
//        for (int i = 0; i< arr.size();i++){
//            if(i%2 != 0){ left.add(arr.get(i)); }
//            else{ right.add(arr.get(i)); }
//        }
//
//        left = sort(left);
//        right = sort(right);
//
//        return merge(left, right);
//    }
//
//    public static ArrayList<Double> merge(ArrayList<Double> left ,ArrayList<Double> right){
//        ArrayList<Double> ret = new ArrayList<>();
//        while(!left.isEmpty() && !right.isEmpty()){
//            if(left.get(0)<=right.get(0)){ ret.add(left.get(0)); left.remove(0); }
//            else{ ret.add(right.get(0)); right.remove(0); }
//        }
//        while(!left.isEmpty()){
//            ret.add(left.get(0));
//            left.remove(0);
//        }
//        while(!right.isEmpty()){
//            ret.add(right.get(0));
//            right.remove(0);
//        }
//        return ret;
//    }

