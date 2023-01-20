package droiidpelaez.westernproject.Roles;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Roles {
    private static List<Roles> allRoles = new ArrayList<>();
    private static HashMap<String, Roles> playerRole = new HashMap<>();

    private String roleName;

    public Roles(String name){
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
    public static Roles getPlayerRole(Player p){
        if(!playerRole.containsKey(p.getUniqueId().toString())){
            return null;
        }
        return playerRole.get(p.getUniqueId().toString());
    }
    //getplayer
    //initiate team




}
