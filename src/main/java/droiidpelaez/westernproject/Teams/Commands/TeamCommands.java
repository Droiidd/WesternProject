package droiidpelaez.westernproject.Teams.Commands;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Roles.RoleUtils;
import droiidpelaez.westernproject.Teams.Utils.Team;
import droiidpelaez.westernproject.Teams.Utils.TeamUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TeamCommands implements CommandExecutor {

    private static List<Team> teamList = Team.getAllTeams();
    private Core plugin;
    private static HashMap<String, String> teamInvites = Team.getTeamInvites();

    public TeamCommands(Core plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                p.sendMessage(ChatColor.GRAY+"Try:");
                p.sendMessage(ChatColor.YELLOW+"/team help");
                p.sendMessage(ChatColor.YELLOW+"/team info");
                p.sendMessage(ChatColor.YELLOW+"/team create");
                return true;
            }


    // ===== CREATE =====
            if(args[0].toLowerCase().compareTo("create") == 0){
                // ===== ERROR CHECKING =====
                if(args.length != 2){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/team create {Team Name}");
                    return true;
                }
                if(Team.hasTeam(p.getUniqueId().toString())){
                    p.sendMessage(ChatColor.RED+"You are already in a team!");
                    return true;
                }
                //String teamName = ChatColor.GRAY+"[" +args[1].trim() +"]"+ChatColor.RESET;
                String teamName = args[1].trim();
                //Checking name availability
                if(!Team.isNameAvail(teamName)){
                        p.sendMessage(ChatColor.RED+"Team name already exists!");
                        return true;
                }
                // ===== TEAM INFORMATION DISPLAY =====
                Team newTeam = new Team(teamName, "white", plugin);
                newTeam.addPlayer(p.getUniqueId().toString());
                for(int i = 0; i < teamList.size(); i++){
                    System.out.println(teamList.get(i).getTeamName());
                    System.out.println(teamList.size());
                }

                p.sendMessage(ChatColor.BOLD+""+ ChatColor.GREEN+ "Team \""+teamName+ "\" created!");
                return true;
            }

    // ===== REMOVE =====
            else if(args[0].toLowerCase().compareTo("delete") == 0){
                if(args.length != 1){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/team delete");
                    return true;
                }
                if(!Team.hasTeam(p.getUniqueId().toString())){
                    p.sendMessage(ChatColor.RED+"You are not in a team!");
                    return true;
                }
                Team playersTeam = Team.getTeam(p);
                playersTeam.removeTeam(playersTeam,p);
                p.sendMessage("Team deleted.");
                return true;
            }
            else if(args[0].toLowerCase().compareTo("info") == 0){
                if(args.length > 2 || args.length < 1){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/team info");
                    return true;
                }
                if(args.length == 1){
                    if(!Team.hasTeam(p.getUniqueId().toString())){p.sendMessage(ChatColor.RED+"You are not in a team!"); return true;}

                    Team playerTeam = Team.getTeam(p);
                    List<Player> teamPlayerList = TeamUtils.getTeamPlayers(p);
                    p.sendMessage(ChatColor.AQUA+playerTeam.getTeamName()+ "'s Info:");
                    p.sendMessage(ChatColor.GRAY+"-------------");
                    p.sendMessage(ChatColor.GOLD+"Capacity: "+ChatColor.GRAY+ playerTeam.getTeamCapacity()+"/6");

                    for(int i = 0; i < teamPlayerList.size();i++){
                        if(Team.hasTeam(teamPlayerList.get(i).getUniqueId().toString())){
                            p.sendMessage(Team.getTeam(teamPlayerList.get(i)).getTeamName()+" "+ teamPlayerList.get(i).getDisplayName());
                        }
                        else{
                            p.sendMessage(teamPlayerList.get(i).getDisplayName());
                        }
                    }



                }
                else if(args.length == 2){
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    //This handles offline players, or misspelled players
                    if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); return true;}

                    Team targetTeam = Team.getTeam(target);

                }


            }
            else if (args[0].toLowerCase().compareTo("leave") == 0){
                if(args.length == 1){
                    if(!Team.hasTeam(p.getUniqueId().toString())){
                        p.sendMessage("You do not have a team!");
                        return true;
                    }
                    Team pTeam = Team.getTeam(p);
                    pTeam.removePlayer(p);
                    return true;
                }
                p.sendMessage(ChatColor.RED+"Could not leave team, try again.");
                return true;

            }
            else if (args[0].toLowerCase().compareTo("invite") == 0){
                if(args.length != 2){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/team invite {player}");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[1]);
                //This handles offline players, or misspelled players
                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); return true;}
                if(Team.hasTeam(target.getUniqueId().toString()) == true){
                    p.sendMessage(ChatColor.RED+"Cannot invite player. "+ChatColor.GRAY+target.getDisplayName()+" is already in a team.");
                    return true;
                }
                if(Team.hasTeam(p.getUniqueId().toString()) != true){
                    p.sendMessage(ChatColor.YELLOW+"You are not in a team!");
                    return true;
                }
                Team.invitePlayer(target, p);
                p.sendMessage(ChatColor.YELLOW+"You have invited "+target.getDisplayName()+ " to the team.");
                return true;

            }
            else if(args[0].toLowerCase().compareTo("accept")==0){
                if(args.length != 1){
                    p.sendMessage(ChatColor.YELLOW+ "Incorrect usage, please try: "+ ChatColor.WHITE+"/team accept");
                    return true;
                }
                if(Team.hasTeam(p.getUniqueId().toString())){
                    p.sendMessage(ChatColor.YELLOW+ "You are already in a team!");
                    p.sendMessage("/team leave "+ChatColor.YELLOW+"to leave.");
                    return true;
                }
                Team.acceptInvite(p);
                return true;


            }
            // ===== LAST ONE =====
            else {
                p.sendMessage(ChatColor.GRAY+"Try:");
                p.sendMessage(ChatColor.GRAY+"/team help");
                p.sendMessage(ChatColor.GRAY+"/team info");
                p.sendMessage(ChatColor.GRAY+"/team create");
                return true;
            }


        }


        return true;
    }
}
