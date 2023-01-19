package droiidpelaez.westernproject.Teams.Listeners;

import droiidpelaez.westernproject.Teams.Utils.Team;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OnPlayerChat implements Listener {
    private static List<Team> teamList = Team.getAllTeams();


    @EventHandler
    public static void onPlayerChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        if(Team.hasTeam(p) == true){

            Team playerTeam = Team.getTeam(p);
            String teamName = playerTeam.getName();

            e.setFormat((ChatColor.BOLD+""+ ChatColor.GRAY+"["+teamName+"] ")+ChatColor.RESET+e.getFormat());

        }

    }




}
