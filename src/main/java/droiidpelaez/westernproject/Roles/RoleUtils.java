package droiidpelaez.westernproject.Roles;

import droiidpelaez.westernproject.Teams.Utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoleUtils {

    public static List<Player> getRolePlayers(Player p){
        Role playersRole = Role.getPlayerRole(p);
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        List<Player> listRolePlayers = new ArrayList<>();

        for (int i = 0; i< playerList.size(); i++) {
            if(Role.hasRole(playerList.get(i))){
                if(Role.getPlayerRole(playerList.get(i)).getRoleName().compareTo(playersRole.getRoleName()) == 0) {
                    listRolePlayers.add(playerList.get(i));
                    }
                }
            }
        return listRolePlayers;
        }



}
