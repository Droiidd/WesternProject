package droiidpelaez.westernproject.Teams;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

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

    // ==== UTIL METHODS ====
    public static Team getTeam(Player p){
        if(!hasTeam(p.getUniqueId().toString())){return null;}
        return playerTeam.get(p.getUniqueId().toString());
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
    public void removeTeam(Team removedTeam){
        allTeams.remove(removedTeam);
    }
    public void addPlayer(String pId){
        if(teamCapacity == 0){
            //teamOfficers.put(p.getUniqueId().toString(), ChatColor.RED+"Captain");
            playerTeam.put(pId, this);
            pTeamStringList.put(pId, this.teamName);
            teamCapacity++;
            Player target = GlobalUtils.getPlayerFromString(pId);
            if(target != null){
                GlobalUtils.loadPlayerStatsDisplay(target);
            }
            System.out.println("Oops -> team add player");

        }
        else if(teamCapacity <= 3-1){
            //teamOfficers.put(p.getUniqueId().toString(),ChatColor.GRAY+ "Member");
            playerTeam.put(pId, this);
            pTeamStringList.put(pId, this.teamName);
            teamCapacity++;
            Player target = GlobalUtils.getPlayerFromString(pId);
            if(target != null){
                GlobalUtils.loadPlayerStatsDisplay(target);
            }
            System.out.println("Oops - team add player");
        }
    }
    public void removePlayer(Player p){
        if(hasTeam(p.getUniqueId().toString()) ){
            if(teamCapacity == 1) {
                p.sendMessage( ChatColor.GRAY+ getTeam(p).teamName + ChatColor.DARK_AQUA+ " has been disbanded");
                teamCapacity--;
                plugin.removeTeam(getTeam(p).teamName, p.getUniqueId().toString());
                pTeamStringList.remove(p.getUniqueId().toString());
                teamInfo.remove(getTeam(p).teamName);
                playerTeam.remove(p.getUniqueId().toString());
                removeTeam(getTeam(p));
                GlobalUtils.loadPlayerStatsDisplay(p);
            }
            else{
                p.sendMessage( ChatColor.DARK_AQUA+"Successfully left the team");

                List<Player> teamPlayerList = getPlayerList(getTeam(p).getTeamName());
                for(int i = 0;i<teamPlayerList.size();i++){
                    teamPlayerList.get(i).sendMessage(ChatColor.DARK_AQUA+p.getDisplayName()+ChatColor.GRAY+" has left the crew...");
                }
                plugin.removeTeam(getTeam(p).teamName, p.getUniqueId().toString());
                playerTeam.remove(p.getUniqueId().toString());
                pTeamStringList.remove(p.getUniqueId().toString());
                GlobalUtils.loadPlayerStatsDisplay(p);
                teamCapacity--;
            }

        }
    }

    public static List<Player> getPlayerList(String targetTeamName){
        Iterator hmIterator = pTeamStringList.entrySet().iterator();
        List<String> targetTeamPlayerList = new ArrayList<>();
        List<Player> targetTeamPlayers = new ArrayList<>();
        while(hmIterator.hasNext()){
            Map.Entry map = (Map.Entry)hmIterator.next();
            String playerId =(String) map.getKey();
            String teamName =(String) map.getValue();

            if(teamName.compareTo(targetTeamName)==0){
                targetTeamPlayerList.add(playerId);
            }
        }
        for (int i=0; i< targetTeamPlayerList.size();i++){
            Player p = GlobalUtils.getPlayerFromString(targetTeamPlayerList.get(i));
            if(p == null){
                System.out.println("Player offline :(");
            }
            targetTeamPlayers.add(p);
        }
        return targetTeamPlayers;
    }

    // === Invitational ===
    public static void invitePlayer(Player target, Player p){
        if(hasTeam(target.getUniqueId().toString())){
            target.sendMessage(ChatColor.DARK_AQUA +"You are already in a team!");
        }
        Team proposedTeam = getTeam(p);
        if(!teamInvites.containsKey(target.getUniqueId().toString())){
            teamInvites.put(target.getUniqueId().toString(), p.getUniqueId().toString());
            target.sendMessage(ChatColor.DARK_AQUA +"You are invited to join, "+ ChatColor.GRAY+proposedTeam.getTeamName());
        }
        else{
            teamInvites.replace(target.getUniqueId().toString(), p.getUniqueId().toString());
            target.sendMessage(ChatColor.DARK_AQUA+"You are invited to join, "+ ChatColor.GRAY+proposedTeam.getTeamName());
        }

    }
    public static void acceptInvite(Player invPlayer){
        if(!teamInvites.containsKey(invPlayer.getUniqueId().toString())){
            invPlayer.sendMessage(ChatColor.GRAY+"You have "+ChatColor.DARK_AQUA+"0"+ChatColor.GRAY+  " pending invites!");
        }
        else{
            UUID playerId = UUID.fromString(teamInvites.get(invPlayer.getUniqueId().toString()));
            Player inviter = Bukkit.getServer().getPlayer(playerId);
            if(inviter == null){ invPlayer.sendMessage(ChatColor.GRAY+"This player is not online."); }
            else{
                Team proposedTeam = getTeam(inviter);
                teamInvites.remove(invPlayer.getUniqueId().toString());
                proposedTeam.addPlayer(invPlayer.getUniqueId().toString());
                //invPlayer.sendMessage(ChatColor.DARK_AQUA+"Successfully joined "+ChatColor.GRAY+ proposedTeam.getTeamName()+ChatColor.DARK_AQUA+"!");
                List<Player> teamPlayerList = getPlayerList(getTeam(invPlayer).getTeamName());
                for(int i = 0;i<teamPlayerList.size();i++){
                    teamPlayerList.get(i).sendMessage(ChatColor.DARK_AQUA+invPlayer.getDisplayName()+ChatColor.GRAY+" has joined the crew!");
                }


            }
        }
    }

}
