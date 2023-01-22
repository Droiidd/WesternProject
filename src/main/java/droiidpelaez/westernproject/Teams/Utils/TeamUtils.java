package droiidpelaez.westernproject.Teams.Utils;

import droiidpelaez.westernproject.CoreUtils.ConfigManager;
import droiidpelaez.westernproject.Roles.Roles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class TeamUtils {

    private HashMap<String, String> teamInfo = Team.getTeamInfo();
    private HashMap<String, String> playerTeamList = Team.getPTeamStringList();
    private List<Team> teamList = new ArrayList<>();

    public static List<Player> getTeamPlayers(Player p){
        Team playersTeam = Team.getTeam(p);
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        List<Player> teamPlayerList = new ArrayList<>();

        for (int i = 0; i< playerList.size(); i++) {
            if(Team.hasTeam(playerList.get(i).getUniqueId().toString())){
                if(Team.getTeam(playerList.get(i)).getTeamName().compareTo(playersTeam.getTeamName()) == 0) {
                    teamPlayerList.add(playerList.get(i));
                }
            }
        }
        return teamPlayerList;
    }

    public void saveTeams(ConfigManager teamConfig){
        for(Map.Entry<String, String> entry : teamInfo.entrySet()){
            teamConfig.playerCFG.set("teamData."+entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String,String> entry : playerTeamList.entrySet()){
            teamConfig.playerCFG.set("playerData."+entry.getKey(),entry.getValue());
        }
        teamConfig.savePlayers();
    }

    public void loadTeams(ConfigManager teamConfig){
        System.out.println("Attempting to load teams");
        teamConfig.playerCFG.getConfigurationSection("teamData").getKeys(true).forEach(key -> {
            String color = (String) teamConfig.playerCFG.get("teamData."+key);
            System.out.println("Check one");
            Team newTeam = new Team(key, color);
            System.out.println("check 2");
            teamList.add(newTeam);
            System.out.println("check 3");
            System.out.println(teamList.get(0).getTeamName());
            System.out.println(teamList.get(0).getTeamName());
        });
        System.out.println(ChatColor.GREEN+"TEAMS LOADED");
        teamConfig.playerCFG.getConfigurationSection("playerData").getKeys(true).forEach(key -> {
            String teamName = (String) teamConfig.playerCFG.get("playerData."+key);
            for(int i = 0; i< teamList.size(); i++){
                if(teamName.compareTo(teamList.get(i).getTeamName())==0){
                    teamList.get(i).addPlayer(key);
                    System.out.printf(ChatColor.LIGHT_PURPLE+"TEAM LOADED!");
                }
            }
        });
        System.out.println(ChatColor.GREEN+"TEAMS CREATED");
    }

    public boolean checkPlayerMaps(){
        if(!teamInfo.isEmpty() && !playerTeamList.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean checkPlayerFileData(ConfigManager teamConfig){
        if(teamConfig.playerCFG.contains("teamData") && teamConfig.playerCFG.contains("playerData")){
            System.out.println(ChatColor.RED+"DATA FOUND?");
            return true;
        }
        return false;
    }





}
