package droiidpelaez.westernproject.Teams.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Team {
    private static List<Team> allTeams = new ArrayList<>();
    private static List<String> teamsPlayers = new ArrayList<>();
    private static List<String> teamsPlayersUuid = new ArrayList<>();
    private static HashMap<String, Team> playerTeam = new HashMap<>();
    private static HashMap<String, String> teamOfficers = new HashMap<>();
    private static HashMap<String,String> teamInvites = new HashMap<>();
    private static String teamName;
    private static Integer teamCapacity;

    public Team(String teamName){
        this.teamName = teamName.trim();
        allTeams.add(this);
        teamCapacity = 0;
    }
    // ===== Getters =====
    public static List<Team> getAllTeams(){ return allTeams; }
    public  List<String>getAllPlayers(){ return teamsPlayers; }
    public List<String> getAllUuid(){ return teamsPlayersUuid; }
    public static HashMap<String, String> getTeamOfficers(){return teamOfficers;}
    public static HashMap<String, String> getTeamInvites(){ return teamInvites; }
    public Integer getTeamCapacity(){return teamCapacity; }

    public void addPlayer(Player p){
        if(teamCapacity == 0){
            p.sendMessage(ChatColor.YELLOW+"Successfully joined "+ChatColor.WHITE+teamName+"!");
            teamOfficers.put(p.getUniqueId().toString(), ChatColor.RED+"Captain");
            playerTeam.put(p.getUniqueId().toString(), this);
            teamsPlayers.add(p.getDisplayName());
            teamsPlayersUuid.add(p.getUniqueId().toString());
            teamCapacity++;
        }
        else if(teamCapacity <= 3-1){
            p.sendMessage(ChatColor.YELLOW+"Successfully joined "+ChatColor.WHITE+teamName+"!");
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
                p.sendMessage("The team has been disbanded");
                removeTeam(getTeam(p), p);
                teamCapacity--;
                playerTeam.remove(p.getUniqueId().toString());
                teamOfficers.remove(p.getUniqueId().toString());
                teamsPlayersUuid.remove(p.getUniqueId().toString());
                teamsPlayers.remove(p.getDisplayName());
            }
            else{
                p.sendMessage("Successfully left the team");
                playerTeam.remove(p.getUniqueId().toString());
                teamOfficers.remove(p.getUniqueId().toString());
                teamsPlayersUuid.remove(p.getUniqueId().toString());
                teamsPlayers.remove(p.getDisplayName());
                teamCapacity--;
            }

        }
    }

    public static void invitePlayer(Player target, Player p){
        if(hasTeam(target) == true){
            target.sendMessage(ChatColor.YELLOW+"You are already in a team!");
        }
        Team proposedTeam = getTeam(p);
        if(teamInvites.containsKey(target.getUniqueId().toString()) != true){
            teamInvites.put(target.getUniqueId().toString(), p.getUniqueId().toString());
            target.sendMessage(ChatColor.YELLOW+"You are invited to join, "+ ChatColor.GRAY+proposedTeam.getName());
        }
        else{
            teamInvites.replace(target.getUniqueId().toString(), p.getUniqueId().toString());
            target.sendMessage(ChatColor.YELLOW+"You are invited to join, "+ ChatColor.GRAY+proposedTeam.getName());
        }

    }
    public static void acceptInvite(Player invPlayer){
        if(hasTeam(invPlayer) == true){
            invPlayer.sendMessage(ChatColor.YELLOW+"You are already in a team!");
        }
        if(teamInvites.containsKey(invPlayer.getUniqueId().toString()) != true){
            invPlayer.sendMessage(ChatColor.YELLOW+"You have no pending invites!");
        }
        else{
            UUID playerId = UUID.fromString(teamInvites.get(invPlayer.getUniqueId().toString()));
            Player inviter = Bukkit.getServer().getPlayer(playerId);
            if(inviter == null){ invPlayer.sendMessage(ChatColor.GRAY+"This player is not online."); }
            else{
                Team proposedTeam = getTeam(inviter);
                proposedTeam.addPlayer(invPlayer);
            }

        }
    }

    public static boolean hasTeam(Player p){
        return playerTeam.containsKey(p.getUniqueId().toString());
    }
    public String getName(){
        return teamName;
    }

    public static Team getTeam(Player p){
        if(hasTeam(p) == false){return null;}
        return playerTeam.get(p.getUniqueId().toString());
    }
    public static void removeTeam(Team removedTeam, Player p){
        allTeams.remove(removedTeam);
    }



}
