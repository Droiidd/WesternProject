package droiidpelaez.westernproject.Roles.Commands;

import droiidpelaez.westernproject.Roles.Role;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                }
                p.sendMessage("You have a role");



            }



        }




        return true;
    }
}
