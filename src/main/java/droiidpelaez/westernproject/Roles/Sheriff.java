package droiidpelaez.westernproject.Roles;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sheriff {
    private static HashMap<String, String> sheriffPlayerList = new HashMap<>();
    private static HashMap<String, Sheriff> playerSheriff = new HashMap<>();
    private List<Sheriff> sheriffObjList = new ArrayList<>();

    public Sheriff(){
        sheriffObjList.add(this);
    }

    public String getRoleDisplayName(){
        String name = ChatColor.GOLD+""+ChatColor.BOLD+ "Sheriff "+ChatColor.RESET;
        return name;

    }
    public String getRoleName(){
        return("Sheriff");
    }
    public static boolean isSheriff(String playerId){
        return sheriffPlayerList.containsKey(playerId);
    }
    public void addSheriff(Player p){
        String uuid = p.getUniqueId().toString();
        sheriffPlayerList.put(uuid, getRoleName());
        p.setPlayerListName(getRoleDisplayName()+ p.getDisplayName());
        p.sendMessage("You are now a sheriff!");
    }

    public static Sheriff getSheriff(String playerId){
        if(!isSheriff(playerId)){
            return null;
        }
        return playerSheriff.get(playerId);
    }






}
