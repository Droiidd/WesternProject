package droiidpelaez.westernproject.Teams.Utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Team {
    private static List<Team> allTeams = new ArrayList<>();
    private static HashMap<String, Team> playerTeam = new HashMap<>();

    private static String teamName;

    public Team(String teamName){
        this.teamName = teamName.trim();
        allTeams.add(this);
    }

    public static List<Team> getAllTeams(){ return allTeams; }

    public String getName(){
        return teamName;
    }
    public void addPlayer(Player p){
        playerTeam.put(p.getUniqueId().toString(), this);
    }

    public void remove(Player p){
        if(hasTeam(p) ){
            playerTeam.remove(p.getUniqueId().toString());
        }
    }

    public static boolean hasTeam(Player p){
        return playerTeam.containsKey(p.getUniqueId().toString());
    }

    public static Team getTeam(Player p){
        if(hasTeam(p) == true){return null;}
        return playerTeam.get(p.getUniqueId().toString());
    }


}
