package droiidpelaez.westernproject.Teams.Utils;

import droiidpelaez.westernproject.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Team {
    private static List<Team> allTeams = new ArrayList<>();
    private static HashMap<String, Team> playerTeam = new HashMap<>();
    //private static HashMap<String, String> teamOfficers = new HashMap<>();
    private static HashMap<String,String> teamInvites = new HashMap<>();
    private static HashMap<String,String> teamInfo = new HashMap<>();
    private static HashMap<String, String> pTeamStringList = new HashMap<>();
    private Core plugin;
    private final String teamName;
    private final String teamColor;
    private Integer teamCapacity;

    public Team(String teamName,String teamColor, Core plugin){
        this.teamName = teamName.trim();
        this.teamColor = teamColor;
        this.plugin = plugin;
        teamInfo.put(teamName,teamColor);
        allTeams.add(this);
        this.teamCapacity = 0;
        //teamsPlayers = new ArrayList<>();
    }
//    public Team(String teamName,String teamColor, String playerName){
//        this.teamName = teamName.trim();
//        this.teamColor = teamColor.trim();
//        allTeams.add(this);
//        this.teamCapacity = 0;
//        addPlayer(playerName);
//    }
    // ===== Getters =====
    public static List<Team> getAllTeams(){ return allTeams; }
    public static HashMap<String, String> getTeamInfo(){ return teamInfo; }
    public static HashMap<String, String> getPTeamStringList(){ return pTeamStringList; }
    //public static HashMap<String, String> getTeamOfficers(){return teamOfficers;}
    public static HashMap<String, String> getTeamInvites(){ return teamInvites; }
    public Integer getTeamCapacity(){return teamCapacity; }
    public static boolean hasTeam(String pId){
        return playerTeam.containsKey(pId);
    }
    public String getTeamName(){
        return teamName;
    }
    public static Team getTeam(Player p){
        if(!hasTeam(p.getUniqueId().toString())){return null;}
        return playerTeam.get(p.getUniqueId().toString());
    }
    public void removeTeam(Team removedTeam, Player p){
        allTeams.remove(removedTeam);
    }
    public static boolean isNameAvail(String teamName){
        if(allTeams.size() == 0){
            return true;
        }
        for (int i = 0;i<allTeams.size();i++){
            if(allTeams.get(i).getTeamName().toLowerCase().compareTo(teamName.toLowerCase())==0) {
                return false;
            }
        }
        return true;
    }
//    public List<String> getTeamPlayers(){
//        return this.teamsPlayers;
//    }
    public void addPlayer(String pId){
        if(teamCapacity == 0){
            //teamOfficers.put(p.getUniqueId().toString(), ChatColor.RED+"Captain");
            playerTeam.put(pId, this);
            pTeamStringList.put(pId, this.teamName);
            //teamsPlayers.add(p.getUniqueId().toString());
            teamCapacity++;
        }
        else if(teamCapacity <= 3-1){
            //teamOfficers.put(p.getUniqueId().toString(),ChatColor.GRAY+ "Member");
            playerTeam.put(pId, this);
            //teamsPlayers.add(p.getUniqueId().toString());
            teamCapacity++;

        }


    }
    public void removePlayer(Player p){
        TeamUtils teamUtils = new TeamUtils(plugin);
        if(hasTeam(p.getUniqueId().toString()) ){
            if(teamCapacity == 1) {
                p.sendMessage("The team has been disbanded");
                teamCapacity--;
                plugin.removeTeam(getTeam(p).teamName, p.getUniqueId().toString());
                pTeamStringList.remove(p.getUniqueId().toString());
                teamInfo.remove(getTeam(p).teamName);
                playerTeam.remove(p.getUniqueId().toString());
                removeTeam(getTeam(p), p);
                //teamsPlayers.remove(p.getUniqueId().toString());
                //teamOfficers.remove(p.getUniqueId().toString());
            }
            else{
                p.sendMessage("Successfully left the team");
                playerTeam.remove(p.getUniqueId().toString());
                pTeamStringList.remove(p.getUniqueId().toString());
                //teamsPlayers.remove(p.getUniqueId().toString());
                //teamOfficers.remove(p.getUniqueId().toString());
                teamCapacity--;
            }

        }
    }
    public static void invitePlayer(Player target, Player p){
        if(hasTeam(target.getUniqueId().toString())){
            target.sendMessage(ChatColor.YELLOW+"You are already in a team!");
        }
        Team proposedTeam = getTeam(p);
        if(!teamInvites.containsKey(target.getUniqueId().toString())){
            teamInvites.put(target.getUniqueId().toString(), p.getUniqueId().toString());
            target.sendMessage(ChatColor.YELLOW+"You are invited to join, "+ ChatColor.GRAY+proposedTeam.getTeamName());
        }
        else{
            teamInvites.replace(target.getUniqueId().toString(), p.getUniqueId().toString());
            target.sendMessage(ChatColor.YELLOW+"You are invited to join, "+ ChatColor.GRAY+proposedTeam.getTeamName());
        }

    }
    public static void acceptInvite(Player invPlayer){
        if(hasTeam(invPlayer.getUniqueId().toString())){
            invPlayer.sendMessage(ChatColor.YELLOW+"You are already in a team!");
        }
        if(!teamInvites.containsKey(invPlayer.getUniqueId().toString())){
            invPlayer.sendMessage(ChatColor.YELLOW+"You have no pending invites!");
        }
        else{
            UUID playerId = UUID.fromString(teamInvites.get(invPlayer.getUniqueId().toString()));
            Player inviter = Bukkit.getServer().getPlayer(playerId);
            if(inviter == null){ invPlayer.sendMessage(ChatColor.GRAY+"This player is not online."); }
            else{
                Team proposedTeam = getTeam(inviter);
                proposedTeam.addPlayer(invPlayer.getUniqueId().toString());
            }
        }
    }

}
