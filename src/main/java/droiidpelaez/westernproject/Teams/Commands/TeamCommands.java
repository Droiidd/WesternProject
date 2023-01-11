package droiidpelaez.westernproject.Teams.Commands;

import droiidpelaez.westernproject.Teams.Utils.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TeamCommands implements CommandExecutor {

    private static List<Team> teamList = Team.getAllTeams();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                p.sendMessage(ChatColor.RED+ "Incorrect Usage");
                p.sendMessage(ChatColor.GRAY+"Try:");
                p.sendMessage(ChatColor.GRAY+"/team help");
                p.sendMessage(ChatColor.GRAY+"/team info");
                p.sendMessage(ChatColor.GRAY+"/team create");
                return true;
            }


    // ===== CREATE =====
            if(args[0].toLowerCase().compareTo("create") == 0){
                if(args.length < 2 || args.length > 2){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/team create {Team Name}");
                    return true;
                }
                if(Team.hasTeam(p) == true){
                    p.sendMessage(ChatColor.RED+"You are already in a team!");
                    return true;
                }
                String teamName = args[1];
                //Checking name availability
                for(int i = 0; i < teamList.size(); i++){
                    if(teamName.equals(teamList.get(i).getName()) == true){
                        p.sendMessage(ChatColor.RED+"Team name already exists!");
                        return true;
                    }
                }

                Team newTeam = new Team(teamName);
                newTeam.addPlayer(p);
                for(int i = 0; i < teamList.size(); i++){
                    System.out.println(teamList.get(i).getName());
                    System.out.println(teamList.size());
                }

                p.sendMessage(ChatColor.BOLD+""+ ChatColor.GREEN+ "Team \""+teamName+ "\" created!");
                return true;
            }

    // ===== REMOVE =====
            else if(args[0].toLowerCase().compareTo("delete") == 0){
                if(args.length > 1 || args.length < 1){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/team delete");
                    return true;
                }
                if(Team.hasTeam(p) != true){
                    p.sendMessage(ChatColor.RED+"You are not in a team!");
                    return true;
                }

                Team playersTeam = Team.getTeam(p);
                Team.removeTeam(playersTeam,p);
                p.sendMessage("Team deleted.");
                return true;

            }
            else if(args[0].toLowerCase().compareTo("info") == 0){
                if(args.length > 1 || args.length < 1){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/team info");
                    return true;
                }
                if(Team.hasTeam(p) == false){p.sendMessage(ChatColor.RED+"You are not in a team!"); return true;}

                Team playerTeam = Team.getTeam(p);

                List<String> playerList = playerTeam.getAllPlayers();
                p.sendMessage(ChatColor.AQUA+playerTeam.getName()+ "'s Info:");
                p.sendMessage(ChatColor.GRAY+"-------------");

                for(int i = 0; i < playerList.size(); i++){
                    p.sendMessage((i+1) + ".) "+  playerList.get(i));
                }





            }
            // ===== LAST ONE =====
            else {
                p.sendMessage(ChatColor.RED+ "Incorrect Usage");
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
