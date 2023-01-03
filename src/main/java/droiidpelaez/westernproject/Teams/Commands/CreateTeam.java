package droiidpelaez.westernproject.Teams.Commands;

import droiidpelaez.westernproject.Teams.Utils.TeamUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CreateTeam implements CommandExecutor {
    private static HashMap<String,String> teamList = TeamUtils.ListAllTeams();
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

                String teamName = args[1];
                p.sendMessage(ChatColor.GRAY+ "Team \""+teamName+ "\" created!");
                teamList.put(p.getUniqueId().toString(), teamName);


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
