package droiidpelaez.westernproject.PlayerCore;

import com.sun.org.apache.xpath.internal.operations.Bool;
import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
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
    private static HashMap<String, Double> playerBountyList = new HashMap<String, Double>();
    private  static HashMap<String, String> playerUUIDList = new HashMap<>();
    private Player p;


    public static HashMap<String, Boolean> getBleedList(){
        return bleedList;
    }
    public static HashMap<String, String> getPlayerList(){
        return playerUUIDList;
    }
    public static HashMap<String, Double> getPlayerBountyList(){
        return playerBountyList;
    }
    public static HashMap<String, Boolean> getCrippleList(){
        return crippleList;
    }
    public static HashMap<String, Boolean> getWantedList(){
        return wantedList;
    }

    public PlayerCore(Player p, Boolean bleeding, Boolean crippled, Boolean wanted, Double bounty) {
        allPlayerCores.add(this);
        this.p = p;
        bleedList.put(p.getUniqueId().toString(), bleeding);
        crippleList.put(p.getUniqueId().toString(), crippled);
        wantedList.put(p.getUniqueId().toString(), wanted);
        playerBountyList.put(p.getUniqueId().toString(), bounty);
        playersCoreList.put(p.getUniqueId().toString(), this);
        playerUUIDList.put(p.getUniqueId().toString(), p.getUniqueId().toString());
    }
    public void updatePlayerCore(Player p, Boolean bleeding, Boolean crippled, Boolean wanted, Double bounty ){
        bleedList.replace(p.getUniqueId().toString(), bleeding);
        crippleList.replace(p.getUniqueId().toString(), crippled);
        wantedList.replace(p.getUniqueId().toString(), wanted);
        playerBountyList.replace(p.getUniqueId().toString(), bounty);
        playerUUIDList.replace(p.getUniqueId().toString(), p.getUniqueId().toString());
    }
    public static void loadPlayerCores(String id, Boolean bleed, Boolean cripple, Boolean wanted, Double bounty){
        bleedList.put(id, bleed);
        crippleList.put(id, cripple);
        wantedList.put(id, wanted);
        playerBountyList.put(id, bounty);
        playerUUIDList.put(id, id);
    }
    public List<PlayerCore> getPlayerCoreList(){
        return allPlayerCores;
    }
    public static Boolean hasPlayer(Player player){
        return playerBountyList.containsKey(player.getUniqueId().toString());
    }
    public static PlayerCore getPlayerCore(Player player){
        return playersCoreList.get(player.getUniqueId().toString());
    }
    public Player getPlayer(){
        return p;
    }
    public Boolean getPlayerBleedStat(){
        return bleedList.get(p.getUniqueId().toString());
    }
    public Boolean getPlayerCrippleStat(){
        return crippleList.get(p.getUniqueId().toString());
    }
    public Boolean getPlayerWantedStat(){
        return wantedList.get(p.getUniqueId().toString());
    }
    public Double getPlayerBounty(Player player){
        return playerBountyList.get(player.getUniqueId().toString());
    }
    public void updateBleed(Boolean newStat){
        bleedList.replace(p.getUniqueId().toString(), newStat);
    }
    public void updateCripple(Boolean newStat){
        crippleList.replace(p.getUniqueId().toString(), newStat);
    }
    public void updateWanted(Boolean newStat){
        wantedList.replace(p.getUniqueId().toString(), newStat);
    }
    public void updateBounty(Double bountyInc){
        bountyInc += playerBountyList.get(p.getUniqueId().toString());
        playerBountyList.replace(p.getUniqueId().toString(), bountyInc);
    }

}

