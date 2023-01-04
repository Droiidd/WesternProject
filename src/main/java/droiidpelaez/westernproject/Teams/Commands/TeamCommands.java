package droiidpelaez.westernproject.Teams.Commands;

import droiidpelaez.westernproject.Teams.Utils.Team;
import droiidpelaez.westernproject.Teams.Utils.TeamUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class TeamCommands implements CommandExecutor {
    private static HashMap<String,String> teamList = TeamUtils.ListAllTeams();
    private static List<Team> teamList2 = Team.getAllTeams();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                p.sendMessage("Incorrect Usage");
                return true;
            }



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
                for(int i = 0; i < teamList2.size(); i++){
                    if(teamName.equals(teamList2.get(i).getName()) == true){
                        p.sendMessage(ChatColor.RED+"Team name already exists!");
                        return true;
                    }
                }

                Team newTeam = new Team(teamName);
                newTeam.addPlayer(p);
                for(int i = 0; i < teamList2.size(); i++){
                    System.out.println(teamList2.get(i).getName());
                    System.out.println(teamList2.size());
                }



                p.sendMessage(ChatColor.GRAY+ "Team \""+teamName+ "\" created!");




                return true;
            }





            else if(args[0].toLowerCase().compareTo("delete") == 0){
                p.sendMessage("Team deleted.");
                return true;
            }




        }





        return true;
    }
}
