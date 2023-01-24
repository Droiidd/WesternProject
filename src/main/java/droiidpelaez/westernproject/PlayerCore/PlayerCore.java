package droiidpelaez.westernproject.PlayerCore;

import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.CoreUtils.ScoreboardUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
    public void updatePlayerCore(Player p, Boolean bleeding, Boolean crippled, Boolean wanted, Integer bounty ){
        bleedList.replace(p.getUniqueId().toString(), bleeding);
        crippleList.replace(p.getUniqueId().toString(), crippled);
        wantedList.replace(p.getUniqueId().toString(), wanted);
        playerBountyList.replace(p.getUniqueId().toString(), bounty);
        playerUUIDList.replace(p.getUniqueId().toString(), p.getUniqueId().toString());
    }
    public static void loadPlayerCores(String id, Boolean bleed, Boolean cripple, Boolean wanted, Integer bounty){
        bleedList.put(id, bleed);
        crippleList.put(id, cripple);
        wantedList.put(id, wanted);
        playerBountyList.put(id, bounty);
        playerUUIDList.put(id, id);
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

//    public Player getPlayer(){
//        return p;
//    }
    public Boolean getPlayerBleedStat(){
        return bleedList.get(pId);
    }
    public Boolean getPlayerCrippleStat(){
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
    public void updateCripple(Boolean newStat){
        crippleList.replace(pId, newStat);
    }
    public void updateWantedd(Boolean newStat){
        wantedList.replace(pId, newStat);
    }
    public void updateBountyy(Integer newStat){
        Integer newBounty = getPlayerBounty()+ newStat;
        playerBountyList.replace(pId,newBounty);

    }
    public void updateOnlineWanted(Player p,Boolean newStat){
        if(newStat){
            p.setPlayerListName( ChatColor.translateAlternateColorCodes('&', "&4&lWanted&r ")+p.getDisplayName());
        }
        else{
            p.setPlayerListName(p.getDisplayName());
        }
        wantedList.replace(p.getUniqueId().toString(), newStat);
        ScoreboardUtils sb = new ScoreboardUtils();
        sb.loadBanditScoreboard(p);
    }
    public void updateOnlineBounty(Player p,Integer bountyInc){
        bountyInc += playerBountyList.get(p.getUniqueId().toString());
        playerBountyList.replace(p.getUniqueId().toString(), bountyInc);
        ScoreboardUtils sb = new ScoreboardUtils();
        sb.loadBanditScoreboard(p);
    }

}

