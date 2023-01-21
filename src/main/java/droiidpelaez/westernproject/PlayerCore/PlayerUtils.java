package droiidpelaez.westernproject.PlayerCore;

import droiidpelaez.westernproject.CoreUtils.ConfigManager;
import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerUtils {
    private static HashMap<String, Boolean> bleedList = PlayerCore.getBleedList();
    private static HashMap<String, Double> pBountyList = PlayerCore.getPlayerBountyList();
    private static HashMap<String, Boolean> wantedList = PlayerCore.getWantedList();
    private static HashMap<String, Boolean> crippleList = PlayerCore.getCrippleList();
    private static HashMap<String,String> playerList = PlayerCore.getPlayerList();
    public void savePlayerFile(ConfigManager playerConfig){
        for(Map.Entry<String, Boolean> entry : bleedList.entrySet()){
            playerConfig.playerCFG.set("bloodData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Boolean> entry : crippleList.entrySet()){
            playerConfig.playerCFG.set("crippleData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Boolean> entry : wantedList.entrySet()){
            playerConfig.playerCFG.set("wantedData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Double> entry : pBountyList.entrySet()){
            playerConfig.playerCFG.set("bountyData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, String> entry : playerList.entrySet()){
            playerConfig.playerCFG.set("playerData."+entry.getKey(), entry.getValue());
        }
        playerConfig.savePlayers();
    }

    public void restorePlayerFile(ConfigManager playerConfig){
        playerConfig.playerCFG.getConfigurationSection("bloodData").getKeys(false).forEach(key ->{
            Boolean bloodStat = (Boolean) playerConfig.playerCFG.get("bloodData."+key);

            bleedList.put(key, bloodStat);
        });
        playerConfig.playerCFG.getConfigurationSection("crippleData").getKeys(false).forEach(key ->{
            Boolean crippleStat = (Boolean) playerConfig.playerCFG.get("crippleData."+key);

            crippleList.put(key, crippleStat);
        });
        playerConfig.playerCFG.getConfigurationSection("wantedData").getKeys(false).forEach(key ->{
            Boolean wantedStat = (Boolean) playerConfig.playerCFG.get("wantedData."+key);

            wantedList.put(key, wantedStat);
        });
        playerConfig.playerCFG.getConfigurationSection("bountyData").getKeys(false).forEach(key -> {
            Double bounty = (Double) playerConfig.playerCFG.get("bountyData."+key);
            System.out.println(ChatColor.GOLD+"PLAYER INFO: "+key +" "+ bounty);
            pBountyList.put(key, bounty);
        });
        playerConfig.playerCFG.getConfigurationSection("playerData").getKeys(false).forEach(key -> {
            String playerId = (String) playerConfig.playerCFG.get("playerData."+key);
            System.out.println(ChatColor.GOLD+"PLAYER INFO: "+key +" "+ playerId);
            playerList.put(key, playerId);
        });

        for (String playerId : playerList.values()) {
            Player player = GlobalUtils.getPlayerFromString(playerId);
            if(player == null){
                System.out.println(ChatColor.LIGHT_PURPLE+"EROEIR");
            }
            PlayerCore pCore = new PlayerCore(player,bleedList.get(playerId),crippleList.get(playerId),wantedList.get(playerId),pBountyList.get(playerId));
            System.out.printf(ChatColor.LIGHT_PURPLE+"PLAYER LOADED!");
        }
    }

    public boolean checkPlayerMaps(){
        if(!bleedList.isEmpty() && !pBountyList.isEmpty() & !wantedList.isEmpty() && !playerList.isEmpty() && !crippleList.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean checkPlayerFileData(ConfigManager playerConfig){
        if(playerConfig.playerCFG.contains("bloodData") && playerConfig.playerCFG.contains("bountyData")
                && playerConfig.playerCFG.contains("crippleData") && playerConfig.playerCFG.contains("wantedData")
                && playerConfig.playerCFG.contains("playerData")){
            System.out.println(ChatColor.RED+"DATA FOUND");
            return true;
        }
        return false;
    }


}
