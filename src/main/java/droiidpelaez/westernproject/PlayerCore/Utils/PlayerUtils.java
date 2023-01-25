package droiidpelaez.westernproject.PlayerCore.Utils;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.UtilCore.ConfigManager;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Roles.Sheriff;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerUtils {
    private static HashMap<String, Boolean> bleedList = PlayerCore.getBleedList();
    private static HashMap<String, Integer> pBountyList = PlayerCore.getPlayerBountyList();
    private static HashMap<String, Boolean> wantedList = PlayerCore.getWantedList();
    private static HashMap<String, Boolean> crippleList = PlayerCore.getCrippleList();
    private static HashMap<String,String> playerList = PlayerCore.getPlayerList();
    private static HashMap<String,String> sheriffList = Sheriff.getSheriffPlayerList();
    private List<String> playerUuid = new ArrayList<>();
    private Core plugin;

    public PlayerUtils(Core plugin){
        this.plugin = plugin;
    }

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
        for(Map.Entry<String, Integer> entry : pBountyList.entrySet()){
            playerConfig.playerCFG.set("bountyData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, String> entry : playerList.entrySet()){
            playerConfig.playerCFG.set("playerData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, String> entry : sheriffList.entrySet()){
            playerConfig.playerCFG.set("sheriffData."+entry.getKey(), entry.getValue());
        }
        playerConfig.savePlayers();
    }

    public void restorePlayerFile(ConfigManager playerConfig){
        System.out.println(ChatColor.LIGHT_PURPLE+"Loading all player data.");
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
            Integer bounty = (Integer) playerConfig.playerCFG.get("bountyData."+key);
            System.out.println(ChatColor.GOLD+"PLAYER INFO: "+key +" "+ChatColor.RESET+ bounty);
            pBountyList.put(key, bounty);
            System.out.println(ChatColor.GOLD+"PLAYER INFO: "+key +" "+ChatColor.RESET+ bounty);
        });
        playerConfig.playerCFG.getConfigurationSection("playerData").getKeys(false).forEach(key -> {
            String playerId = (String) playerConfig.playerCFG.get("playerData."+key);
            System.out.println(ChatColor.GOLD+"PLAYER INFO: "+key +" "+ChatColor.WHITE+ playerId);
            playerList.put(key, playerId);
            playerUuid.add(playerId);
        });
        playerConfig.playerCFG.getConfigurationSection("sheriffData").getKeys(false).forEach(key -> {
            System.out.println("SHERIFF DATA:");
            String pName = (String) playerConfig.playerCFG.get("sheriffData."+key);
            System.out.println(key + "SHeriff" + pName);

            System.out.println(ChatColor.GOLD+"Creating new sheriff");

            Sheriff newSheriff = new Sheriff(key, pName, plugin);
            System.out.println(ChatColor.GREEN+"SHERRIF CREATED!");
        });

        for (int i =0;i<playerUuid.size();i++) {
            System.out.println("Loading player ID: " + playerUuid.get(i));
            System.out.println("Making player core...");
                //PlayerCore pCore = new PlayerCore(playerUuid.get(i),bleedList.get(playerUuid.get(i)),crippleList.get(playerUuid.get(i)),
                  //      wantedList.get(playerUuid.get(i)),pBountyList.get(playerUuid.get(i)));
                PlayerCore newCore = new PlayerCore(playerUuid.get(i),bleedList.get(playerUuid.get(i)),crippleList.get(playerUuid.get(i)),wantedList.get(playerUuid.get(i)),pBountyList.get(playerUuid.get(i)));
                System.out.println(ChatColor.LIGHT_PURPLE+"PLAYER LOADED!");

        }



    }

    public boolean checkPlayerMaps(){
        if(!bleedList.isEmpty() && !pBountyList.isEmpty() && !wantedList.isEmpty() && !playerList.isEmpty() && !crippleList.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean checkPlayerFileData(ConfigManager playerConfig){
        if(playerConfig.playerCFG.contains("bloodData") && playerConfig.playerCFG.contains("bountyData")
                && playerConfig.playerCFG.contains("crippleData") && playerConfig.playerCFG.contains("wantedData")
                && playerConfig.playerCFG.contains("playerData")){
            System.out.println(ChatColor.LIGHT_PURPLE+"Loading player data file");
            return true;
        }
        return false;
    }
    public void loadJoiningPlayerCore(){
        for (int i =0;i<playerUuid.size();i++) {
            System.out.println("Loading player ID: " + playerUuid.get(i));
            Player player = GlobalUtils.getPlayerFromString(playerUuid.get(i));
            System.out.println("Player loaded!");
            if(player != null){
                System.out.println("Making player core...");
                PlayerCore pCore = new PlayerCore(player.getUniqueId().toString(),bleedList.get(playerUuid.get(i)),crippleList.get(playerUuid.get(i)),
                        wantedList.get(playerUuid.get(i)),pBountyList.get(playerUuid.get(i)));
                System.out.println(ChatColor.LIGHT_PURPLE+"PLAYER LOADED!");

            }
            System.out.println(ChatColor.LIGHT_PURPLE+"Player offline.");

        }
    }





}
