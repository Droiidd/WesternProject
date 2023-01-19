package droiidpelaez.westernproject.Roles;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Role {
    private static List<Role> allRoles = new ArrayList<>();
    private static HashMap<String, Role> playerRole = new HashMap<>();

    private String roleName;

    public Role(String name){
        this.roleName = name;
        allRoles.add(this);
    }
    public void addPlayer(Player p){
        playerRole.put(p.getUniqueId().toString(), this);
        p.sendMessage("Added to role: "+ getRoleName());
    }
    public void removePlayer(Player p){
        //blank for now
    }
    public String getRoleName(){return this.roleName;}

    public static Boolean hasRole(Player p){
        return playerRole.containsKey(p.getUniqueId().toString());
    }
    public static Role getPlayerRole(Player p){
        if(!playerRole.containsKey(p.getUniqueId().toString())){
            return null;
        }
        return playerRole.get(p.getUniqueId().toString());
    }
    //getplayer
    //initiate team




}
