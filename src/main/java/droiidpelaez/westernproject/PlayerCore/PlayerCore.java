package droiidpelaez.westernproject.PlayerCore;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerCore {
    private List<PlayerCore> playerCoreList = new ArrayList<>();
    private HashMap<String, Boolean> bleedList = new HashMap<String, Boolean>();
    private HashMap<String, Boolean> crippleList = new HashMap<String, Boolean>();
    private HashMap<String, Boolean> wantedList = new HashMap<String, Boolean>();
    private HashMap<String, Double> playerBountyList = new HashMap<String, Double>();
    private Player p;

    public PlayerCore(Player p, Boolean bleeding, Boolean crippled, Boolean wanted, Double bounty) {
        playerCoreList.add(this);
        this.p = p;
        bleedList.put(p.getUniqueId().toString(), bleeding);
        crippleList.put(p.getUniqueId().toString(), crippled);
        wantedList.put(p.getUniqueId().toString(), wanted);
        playerBountyList.put(p.getUniqueId().toString(), bounty);
    }
    public List<PlayerCore> getPlayerCoreList(){
        return playerCoreList;
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
    public Double getPlayerBounty(){
        return playerBountyList.get(p.getUniqueId().toString());
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

}

