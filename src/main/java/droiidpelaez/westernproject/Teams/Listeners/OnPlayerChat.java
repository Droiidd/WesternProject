package droiidpelaez.westernproject.Teams.Listeners;

import com.google.common.util.concurrent.Service;
import droiidpelaez.westernproject.Teams.Utils.Team;
import droiidpelaez.westernproject.Teams.Utils.TeamUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashMap;
import java.util.List;

public class OnPlayerChat implements Listener {
    private static List<Team> teamList = Team.getAllTeams();


    @EventHandler
    public static void onPlayerChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        if(Team.hasTeam(p) == true){
            Team playerTeam = Team.getTeam(p);
            String teamName = playerTeam.getName();
            e.setFormat(ChatColor.GRAY+"["+teamName+"] "+e.getFormat());
        }


    }




}
