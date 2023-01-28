package droiidpelaez.westernproject.PlayerCore;

import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerCore {
    private static List<PlayerCore> allPlayerCores = new ArrayList<>();
    private static HashMap<String, PlayerCore> playersCoreList = new HashMap<>();
    private static HashMap<String, Boolean> bleedList = new HashMap<String, Boolean>();
    private static HashMap<String, Boolean> crippleList = new HashMap<String, Boolean>();
    private static HashMap<String, Boolean> wantedList = new HashMap<String, Boolean>();
    private static HashMap<String, Integer> playerBountyList = new HashMap<String, Integer>();
    private  static HashMap<String, String> playerUUIDList = new HashMap<>();
    private String pId;


    public static HashMap<String, Boolean> getBleedList(){
        return bleedList;
    }
    public static HashMap<String, String> getPlayerList(){
        return playerUUIDList;
    }
    public static HashMap<String, Integer> getPlayerBountyList(){
        return playerBountyList;
    }
    public static HashMap<String, Boolean> getCrippleList(){
        return crippleList;
    }
    public static HashMap<String, Boolean> getWantedList(){
        return wantedList;
    }

    public PlayerCore(String pId, Boolean bleeding, Boolean crippled, Boolean wanted, Integer bounty) {
        allPlayerCores.add(this);
        this.pId = pId;
        bleedList.put(pId, bleeding);
        crippleList.put(pId, crippled);
        wantedList.put(pId, wanted);
        playerBountyList.put(pId, bounty);
        playersCoreList.put(pId, this);
        playerUUIDList.put(pId,pId);
    }
    public List<PlayerCore> getPlayerCoreList(){
        return allPlayerCores;
    }
    public static Boolean hasPlayer(String playerId){
        return playerBountyList.containsKey(playerId);
    }
    public static PlayerCore getPlayerCore(String playerId){
        return playersCoreList.get(playerId);
    }
    public Boolean isPlayerBleeding(){
        return bleedList.get(pId);
    }
    public Boolean isPlayerCrippled(){
        return crippleList.get(pId);
    }
    public Boolean isPlayerWanted(){
        return wantedList.get(pId);
    }
    public Integer getPlayerBounty(){
        return playerBountyList.get(pId);
    }
    public void updateBleed(Boolean newStat){
        bleedList.replace(pId, newStat);
    }
    public void updateOnlineCripple(Player p, Boolean newStat){
        if(newStat){
            p.sendMessage(ChatColor.GRAY+"Your legs are "+ChatColor.RED+"broken!");
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3));
            crippleList.put(pId, true);
        }
        else{
            p.sendMessage(ChatColor.GRAY+"Your legs are no longer "+ChatColor.RED+"broken!");
            crippleList.put(pId,false);
        }

    }
    public void updateOnlineBleed(Player p,Boolean newStat){
        if(newStat){
            p.sendMessage(ChatColor.GRAY+"You are now "+ChatColor.RED+"bleeding!");
            bleedList.replace(pId, newStat);
        }
        else{
            p.sendMessage(ChatColor.GRAY+"You patched your wounds");
            bleedList.replace(pId, newStat);
        }

    }
    public void updateCripple(Boolean newStat){
        crippleList.replace(pId, newStat);
    }
    public void updateWanted(Boolean newStat){
        wantedList.replace(pId, newStat);
    }
    public void setBounty(Player p,Integer newStat){
        playerBountyList.replace(pId, newStat);
        GlobalUtils.loadPlayerStatsDisplay(p);
    }
    public void updateBounty(Integer newStat){
        Integer newBounty = getPlayerBounty()+ newStat;
        playerBountyList.replace(pId,newBounty);

    }
    public void updateOnlineWanted(Player p,Boolean newStat){
        if(!newStat){
            p.sendMessage(ChatColor.GRAY+"You are no longer "+ChatColor.RED+"wanted");
            p.setPlayerListName(p.getDisplayName());
            wantedList.replace(p.getUniqueId().toString(), newStat);
            GlobalUtils.loadPlayerStatsDisplay(p);
        }
        else{
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has gone rogue!");
            wantedList.replace(p.getUniqueId().toString(), newStat);
            GlobalUtils.loadPlayerStatsDisplay(p);
        }
    }
    public void updateOnlineBounty(Player p,Integer bountyInc){
        bountyInc += playerBountyList.get(p.getUniqueId().toString());
        playerBountyList.replace(p.getUniqueId().toString(), bountyInc);
        GlobalUtils.loadPlayerStatsDisplay(p);
    }

}

