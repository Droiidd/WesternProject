package droiidpelaez.westernproject.Roles.Commands;

import droiidpelaez.westernproject.Roles.RoleController;
import droiidpelaez.westernproject.Roles.Roles;
import droiidpelaez.westernproject.Roles.RoleUtils;
import droiidpelaez.westernproject.Teams.Utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RoleCommands implements CommandExecutor {
    private final RoleController roleController;

    public RoleCommands(RoleController roleController) {
        this.roleController = roleController;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args[0].toLowerCase().compareTo("create") == 0) {
                if(!Roles.hasRole(p)){
                    String roleName = args[1];
                    Roles newRole = new Roles(roleName.trim());
                    newRole.addPlayer(p);
                    return true;
                }
                p.sendMessage("You have a role");
                return true;
            }
            else if(args[0].toLowerCase().compareTo("list") == 0){
                if(!Roles.hasRole(p)){
                    p.sendMessage(ChatColor.BLUE+ "You do not have a role!");
                    return true;
                }
                Roles playersRole = Roles.getPlayerRole(p);

                p.sendMessage(ChatColor.BLUE+"List of "+playersRole.getRoleName()+"'s");
                p.sendMessage(ChatColor.GRAY+"----------");

                List<Player> listRolePlayers = RoleUtils.getRolePlayers(p);
                for(int i = 0; i < listRolePlayers.size();i++){
                   if(Team.hasTeam(listRolePlayers.get(i).getUniqueId().toString())){
                       p.sendMessage(Team.getTeam(listRolePlayers.get(i)).getTeamName()+" "+ listRolePlayers.get(i).getDisplayName());
                   }
                   else{
                       p.sendMessage(listRolePlayers.get(i).getDisplayName());
                   }
                }


                return true;
            }
            else if(args[0].toLowerCase().compareTo("set") == 0){
                Player target = Bukkit.getServer().getPlayer(args[1]);
                //This handles offline players, or misspelled players
                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); return true;}
                String roleName = args[2];
                roleController.setPlayerRole(p, roleName);



            }



        }




        return true;
    }
}
