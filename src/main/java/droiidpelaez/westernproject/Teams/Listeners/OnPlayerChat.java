package droiidpelaez.westernproject.Teams.Listeners;

import com.google.common.util.concurrent.Service;
import droiidpelaez.westernproject.Teams.Utils.TeamUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashMap;

public class OnPlayerChat implements Listener {
    private static HashMap<String,String> teamList = TeamUtils.ListAllTeams();


    @EventHandler
    public static void onPlayerChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        if(teamList.containsKey(p.getUniqueId().toString())){
            String teamName = teamList.get(p.getUniqueId().toString());
            e.setFormat(ChatColor.GRAY+"["+teamName+"] "+e.getFormat());
        }


    }




}
