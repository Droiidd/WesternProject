package droiidpelaez.westernproject.Roles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RoleUtils {

    public static List<Player> getRolePlayers(Player p){
        Roles playersRole = Roles.getPlayerRole(p);
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        List<Player> listRolePlayers = new ArrayList<>();

        for (int i = 0; i< playerList.size(); i++) {
            if(Roles.hasRole(playerList.get(i))){
                if(Roles.getPlayerRole(playerList.get(i)).getRoleName().compareTo(playersRole.getRoleName()) == 0) {
                    listRolePlayers.add(playerList.get(i));
                    }
                }
            }
        return listRolePlayers;
        }



}
