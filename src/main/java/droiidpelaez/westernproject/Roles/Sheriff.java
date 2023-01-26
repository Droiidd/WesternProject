package droiidpelaez.westernproject.Roles;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sheriff {
    private static HashMap<String, String> sheriffPlayerList = new HashMap<>();
    private static HashMap<String, Sheriff> playerSheriff = new HashMap<>();
    private List<Sheriff> sheriffObjList = new ArrayList<>();
    private static Core plugin;

    public Sheriff(String pId, String pName, Core plugin) {
        sheriffPlayerList.put(pId, pName);
        playerSheriff.put(pId, this);
        this.plugin = plugin;
        sheriffObjList.add(this);
    }

    public static boolean isSheriff(String playerId) {
        return sheriffPlayerList.containsKey(playerId);
    }

    public static Sheriff getSheriff(String playerId) {
        if (!isSheriff(playerId)) {
            return null;
        }
        return playerSheriff.get(playerId);
    }
    public static HashMap<String, String> getSheriffPlayerList(){
        return sheriffPlayerList;
    }

    public void addSheriff(String pId, String pName) {

        sheriffPlayerList.put(pId,pName);
        Player target = GlobalUtils.getPlayerFromString(pId);
        if(target != null){
            target.setPlayerListName(getRoleDisplayName() + target.getDisplayName());
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(target);
        }
        System.out.println("Oops -> sheriff add player");
    }
    public void loadOnlineSheriff(Player target){
        target.setPlayerListName(getRoleDisplayName() + target.getDisplayName());
        ScoreboardUtils sb = new ScoreboardUtils();
        sb.loadPlayerScoreboard(target);
    }

    public void removeSheriff(String playerId) {
        if(isSheriff(playerId)){
            plugin.removeSheriff(playerId);
            sheriffPlayerList.remove(playerId);
            playerSheriff.remove(playerId);
            System.out.println("Good.");
            Player p = GlobalUtils.getPlayerFromString(playerId);
            if(p != null){
                p.setPlayerListName(p.getDisplayName());
                ScoreboardUtils sb = new ScoreboardUtils();
                sb.loadPlayerScoreboard(p);
            }
        }
       // p.sendMessage("You are no longer a sheriff!");
        //remove team main plugin
        System.out.println("BEFORE PLUGIN REMOVE");

        System.out.println("AFTER PLUGIN REMOVE");



    }

    public String getRoleDisplayName() {
        String name = ChatColor.GOLD + "" + ChatColor.BOLD + "Sheriff " + ChatColor.RESET;
        return name;

    }

    public String getRoleName() {
        return ("Sheriff");
    }


}
