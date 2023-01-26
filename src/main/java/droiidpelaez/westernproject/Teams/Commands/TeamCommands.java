package droiidpelaez.westernproject.Teams.Commands;

import droiidpelaez.westernproject.Core;

import droiidpelaez.westernproject.Teams.Team;
import droiidpelaez.westernproject.Teams.Utils.TeamUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class TeamCommands implements CommandExecutor {

    private final static List<Team> teamList = Team.getAllTeams();
    private final Core plugin;
    private final static HashMap<String, String> teamInvites = Team.getTeamInvites();
    public TeamCommands(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                p.sendMessage(ChatColor.GRAY+"==========");
                p.sendMessage(ChatColor.GRAY+"Try:");
                p.sendMessage(ChatColor.DARK_AQUA+"/team help");
                p.sendMessage(ChatColor.DARK_AQUA+"/team create");
                p.sendMessage(ChatColor.GRAY+"==========");
                return true;
            }


    // ===== CREATE =====
            if(args[0].toLowerCase().compareTo("create") == 0){
                // ===== ERROR CHECKING =====
                if(args.length != 2){
                    p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_AQUA+"/team create {team name}");
                    return true;
                }
                if(Team.hasTeam(p.getUniqueId().toString())){
                    p.sendMessage(ChatColor.GRAY+"You are already in a team!");
                    return true;
                }
                String teamName = "[" +args[1].trim() +"]";
                //String teamName = args[1].trim();
                //Checking name availability
                if(!Team.isNameAvail(teamName)){
                        p.sendMessage(ChatColor.GRAY+"Team name already exists!");
                        return true;
                }
                // ===== TEAM INFORMATION DISPLAY =====
                Team newTeam = new Team(teamName, "white", plugin);
                newTeam.addPlayer(p.getUniqueId().toString());
                for(int i = 0; i < teamList.size(); i++){
                    System.out.println(teamList.get(i).getTeamName());
                    System.out.println(teamList.size());
                }

                p.sendMessage(ChatColor.BOLD+""+ ChatColor.DARK_AQUA+ "Team \""+ChatColor.GRAY +teamName+ChatColor.DARK_AQUA+ "\" created!");
                return true;
            }
    // ===== REMOVE =====
            else if(args[0].toLowerCase().compareTo("delete") == 0){
                if(args.length != 1){
                    p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_AQUA+"/team delete");
                    return true;
                }
                if(!Team.hasTeam(p.getUniqueId().toString())){
                    p.sendMessage(ChatColor.GRAY+"You are not in a team!");
                    return true;
                }
                Team playersTeam = Team.getTeam(p);
                playersTeam.removeTeam(playersTeam);
                p.sendMessage(ChatColor.DARK_AQUA+"Team "+ChatColor.GRAY+ playersTeam.getTeamName()+ChatColor.DARK_AQUA+" deleted.");
                return true;
            }
            else if(args[0].toLowerCase().compareTo("info") == 0){
                if(args.length > 2 ){
                    p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_AQUA+"/team info");
                    return true;
                }
                if(args.length == 1){
                    if(!Team.hasTeam(p.getUniqueId().toString())){p.sendMessage(ChatColor.RED+"You are not in a team!"); return true;}

                    Team playerTeam = Team.getTeam(p);
                    List<Player> teamPlayerList = TeamUtils.getTeamPlayers(p);
                    p.sendMessage("");
                    p.sendMessage(ChatColor.GRAY+playerTeam.getTeamName()+ChatColor.DARK_AQUA+ "'s Info:");
                    p.sendMessage(ChatColor.GRAY+"-------------");
                    p.sendMessage(ChatColor.GRAY+"Capacity: "+ChatColor.DARK_AQUA+ playerTeam.getTeamCapacity()+"/6");
                    p.sendMessage(ChatColor.GRAY+"Current "+ChatColor.DARK_AQUA+"Online"+ ChatColor.GRAY+ " players:");
                    p.sendMessage("");
                    for(int i = 0; i < teamPlayerList.size();i++){
                        if(Team.hasTeam(teamPlayerList.get(i).getUniqueId().toString())){
                            p.sendMessage(ChatColor.GRAY+Team.getTeam( teamPlayerList.get(i)).getTeamName()+" "+ teamPlayerList.get(i).getDisplayName());
                        }
                        else{
                            p.sendMessage(teamPlayerList.get(i).getDisplayName());
                        }
                    }



                }
                else if(args.length == 2){
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    //This handles offline players, or misspelled players
                    if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not " +
                            ChatColor.DARK_AQUA+"online."); return true;}
                    Team targetTeam = Team.getTeam(target);
                    List<Player> teamPlayerList = TeamUtils.getTeamPlayers(target);
                    p.sendMessage("");
                    p.sendMessage(ChatColor.GRAY+targetTeam.getTeamName()+ChatColor.DARK_AQUA+ "'s Info:");
                    p.sendMessage(ChatColor.GRAY+"-------------");
                    p.sendMessage(ChatColor.GRAY+"Capacity: "+ChatColor.DARK_AQUA+ targetTeam.getTeamCapacity()+"/6");
                    p.sendMessage(ChatColor.GRAY+"Current "+ChatColor.DARK_AQUA+"Online"+ ChatColor.GRAY+ " players:");
                    p.sendMessage("");
                    for(int i = 0; i < teamPlayerList.size();i++){
                        if(Team.hasTeam(teamPlayerList.get(i).getUniqueId().toString())){
                            p.sendMessage(ChatColor.GRAY+Team.getTeam(teamPlayerList.get(i)).getTeamName()+" "+ teamPlayerList.get(i).getDisplayName());
                        }
                        else{
                            p.sendMessage(teamPlayerList.get(i).getDisplayName());
                        }
                    }
                    //Display target teams info

                }
            }
            else if (args[0].toLowerCase().compareTo("leave") == 0){
                if(args.length == 1){
                    if(!Team.hasTeam(p.getUniqueId().toString())){
                        p.sendMessage(ChatColor.RED+ "You are not in a team!");
                        return true;
                    }
                    Team pTeam = Team.getTeam(p);
                    pTeam.removePlayer(p);
                    return true;
                }
                p.sendMessage(ChatColor.GRAY+"Could not leave team, "+ChatColor.DARK_AQUA+" try again.");
                return true;
            }
            else if (args[0].toLowerCase().compareTo("invite") == 0){
                if(args.length != 2){
                    p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_AQUA+"/team invite {player}");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[1]);
                //This handles offline players, or misspelled players
                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not"+ChatColor.DARK_AQUA+" online."); return true;}
                if(Team.hasTeam(target.getUniqueId().toString())){
                    p.sendMessage(ChatColor.GRAY+"Cannot invite player. "+ChatColor.DARK_AQUA+target.getDisplayName()+ChatColor.GRAY+ " is already in a team.");
                    return true;
                }
                if(!Team.hasTeam(p.getUniqueId().toString())){
                    p.sendMessage(ChatColor.GRAY+"You are not in a team!");
                    return true;
                }
                Team.invitePlayer(target, p);
                p.sendMessage(ChatColor.GRAY+"You have invited "+ChatColor.DARK_AQUA +target.getDisplayName()+ChatColor.GRAY+ " to the team.");
                return true;
            }
            else if(args[0].toLowerCase().compareTo("accept")==0){
                if(args.length != 1){
                    p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_AQUA+"/team accept");
                    return true;
                }
                if(Team.hasTeam(p.getUniqueId().toString())){
                    p.sendMessage(ChatColor.GRAY+ "You are already in a team!");
                    p.sendMessage(ChatColor.DARK_AQUA+ "/team leave "+ChatColor.GRAY+"to leave.");
                    return true;
                }
                Team.acceptInvite(p);
                Team pTeam = Team.getTeam(p);

                return true;
            }
            else if(args[0].toLowerCase().compareTo("help") == 0){
                if(args.length != 1){
                    p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_AQUA+"/team help");
                    return true;
                }
                p.sendMessage(ChatColor.GRAY+"-> To "+ChatColor.DARK_AQUA+"disband" +ChatColor.GRAY+" your team, be the last to leave.");
                p.sendMessage(ChatColor.GRAY+"-> Invite players with "+ChatColor.DARK_AQUA+"/team invite {user}");
                p.sendMessage(ChatColor.GRAY+"-> "+ChatColor.DARK_AQUA+"/team accept"+ChatColor.GRAY+" to accept pending invites");
                p.sendMessage(ChatColor.GRAY+"-> Use "+ChatColor.DARK_AQUA+"/team info"+ChatColor.GRAY+" to view your team info");
                p.sendMessage(ChatColor.GRAY+"-> Use "+ChatColor.DARK_AQUA+"/team info {user}"+ChatColor.GRAY+" to view");
                p.sendMessage(ChatColor.GRAY+"   another players team.");

            }
            // ===== LAST ONE =====
            else {
                p.sendMessage(ChatColor.GRAY+"==========");
                p.sendMessage(ChatColor.GRAY+"Try:");
                p.sendMessage(ChatColor.DARK_AQUA+"/team help");
                p.sendMessage(ChatColor.DARK_AQUA+"/team create");
                p.sendMessage(ChatColor.GRAY+"==========");
                return true;
            }
        }
        return true;
    }
}
