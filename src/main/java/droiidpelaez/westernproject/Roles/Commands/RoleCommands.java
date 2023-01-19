package droiidpelaez.westernproject.Roles.Commands;

import droiidpelaez.westernproject.Roles.Role;
import droiidpelaez.westernproject.Roles.RoleUtils;
import droiidpelaez.westernproject.Teams.Utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RoleCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args[0].toLowerCase().compareTo("create") == 0) {
                if(!Role.hasRole(p)){
                    String roleName = args[1];
                    Role newRole = new Role(roleName.trim());
                    newRole.addPlayer(p);
                    return true;
                }
                p.sendMessage("You have a role");
                return true;
            }
            else if(args[0].toLowerCase().compareTo("list") == 0){
                if(!Role.hasRole(p)){
                    p.sendMessage(ChatColor.BLUE+ "You do not have a role!");
                    return true;
                }
                Role playersRole = Role.getPlayerRole(p);

                p.sendMessage(ChatColor.BLUE+"List of "+playersRole.getRoleName()+"'s");
                p.sendMessage(ChatColor.GRAY+"----------");

                List<Player> listRolePlayers = RoleUtils.getRolePlayers(p);
                for(int i = 0; i < listRolePlayers.size();i++){
                   if(Team.hasTeam(listRolePlayers.get(i))){
                       p.sendMessage(Team.getTeam(listRolePlayers.get(i)).getName()+" "+ listRolePlayers.get(i).getDisplayName());
                   }
                   else{
                       p.sendMessage(listRolePlayers.get(i).getDisplayName());
                   }
                }


                return true;
            }



        }




        return true;
    }
}
