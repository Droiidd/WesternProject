package droiidpelaez.westernproject.Teams.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Team {
    private static List<Team> allTeams = new ArrayList<>();
    private static List<String> teamsPlayers = new ArrayList<>();
    private static List<String> teamsPlayersUuid = new ArrayList<>();
    private static HashMap<String, Team> playerTeam = new HashMap<>();
    private static HashMap<Team, Double> teamBanks = new HashMap<>();

    private static HashMap<String, String> teamOfficers = new HashMap<>();
    private static String teamName;
    private static Integer teamCapacity;

    public Team(String teamName){
        this.teamName = teamName.trim();
        allTeams.add(this);
        teamCapacity = 0;
    }
    public static List<Team> getAllTeams(){ return allTeams; }
    public  List<String>getAllPlayers(){ return teamsPlayers; }
    public List<String> getAllUuid(){ return teamsPlayersUuid; }
    public static HashMap<Team, Double> getTeamAccounts(){ return teamBanks;}
    public static HashMap<String, String> getTeamOfficers(){return teamOfficers;}

    public String getName(){
        return teamName;
    }
    public void addPlayer(Player p){
        if(teamCapacity == 0){
            teamOfficers.put(p.getUniqueId().toString(), ChatColor.RED+"Captain");
            playerTeam.put(p.getUniqueId().toString(), this);
            teamsPlayers.add(p.getDisplayName());
            teamsPlayersUuid.add(p.getUniqueId().toString());
            teamCapacity++;
            p.sendMessage(teamCapacity.toString());
        }
        else if(teamCapacity > 0 || teamCapacity < 2){
            teamOfficers.put(p.getUniqueId().toString(),ChatColor.GRAY+ "Member");
            playerTeam.put(p.getUniqueId().toString(), this);
            teamsPlayers.add(p.getDisplayName());
            teamsPlayersUuid.add(p.getUniqueId().toString());
            teamCapacity++;
        }
        else{
            p.sendMessage("This team is full!");
        }

    }

    public static void removePlayer(Player p){
        if(hasTeam(p) ){
            if(teamCapacity == 1) {
                removeTeam(getTeam(p), p);
                teamCapacity--;
                playerTeam.remove(p.getUniqueId().toString());
                teamOfficers.remove(p.getUniqueId().toString());
                teamsPlayersUuid.remove(p.getUniqueId().toString());
                teamsPlayers.remove(p.getDisplayName());
                p.sendMessage("The team has been disbanded");
            }
            else{
                playerTeam.remove(p.getUniqueId().toString());
                teamCapacity--;
                p.sendMessage("Successfully left the team");
                p.sendMessage(teamCapacity.toString());
            }

        }
    }

    public static boolean hasTeam(Player p){
        return playerTeam.containsKey(p.getUniqueId().toString());
    }

    public static Team getTeam(Player p){
        if(hasTeam(p) == false){return null;}
        return playerTeam.get(p.getUniqueId().toString());
    }
    public static void removeTeam(Team removedTeam, Player p){
        allTeams.remove(removedTeam);
    }

    public static void createTeamBank(Team team, Double amount){
        teamBanks.put(team, amount);
    }
    public static void addFunds(Team team, Double amount){
        teamBanks.replace(team, amount);
    }


}
