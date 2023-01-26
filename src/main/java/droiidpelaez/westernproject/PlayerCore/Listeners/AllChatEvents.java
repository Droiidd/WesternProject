package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Roles.Sheriff;
import droiidpelaez.westernproject.Teams.Team;
import jdk.tools.jlink.plugin.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class AllChatEvents implements Listener {
    private Core plugin;
    private final String wantedDisplay = ChatColor.DARK_RED+""+ChatColor.BOLD+"Wanted ";
    private final String sheriffDisplay = ChatColor.GOLD+"" +ChatColor.BOLD+"Sheriff ";


    public AllChatEvents(Core plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        Sheriff sheriff = Sheriff.getSheriff(p.getUniqueId().toString());
        if(sheriff == null){
            System.out.println("Player is not a sheriff");
        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());

        //Player is a SHERIFF
        if(sheriff.isSheriff(p.getUniqueId().toString())){
            //If a player has a team
            if(Team.hasTeam(p.getUniqueId().toString())){
                Team playerTeam = Team.getTeam(p);
                String teamName = playerTeam.getTeamName();
                e.setFormat(ChatColor.GRAY+teamName+" "+ChatColor.RESET+e.getFormat());
            }
            //Otherwise normal sheriff
            e.setFormat(sheriffDisplay+ChatColor.RESET+e.getFormat());
        }
        //Player is WANTED
        else if(pCore.isPlayerWanted()){

            e.setFormat(wantedDisplay+ChatColor.RESET+e.getFormat());
            //If a player has a team
            if(Team.hasTeam(p.getUniqueId().toString())){
                Team playerTeam = Team.getTeam(p);
                String teamName = playerTeam.getTeamName();
                e.setFormat(ChatColor.GRAY +teamName+" "+ChatColor.RESET+e.getFormat());
            }
            //Otherwise normal wanted

        }
        //Player is NOT WANTED
        else{
            e.setFormat(ChatColor.GRAY+""+ChatColor.BOLD +"Bandit "+ChatColor.RESET+e.getFormat());
            //If player has a team
            if(Team.hasTeam(p.getUniqueId().toString())){
                Team playerTeam = Team.getTeam(p);
                String teamName = playerTeam.getTeamName();
                e.setFormat(ChatColor.GRAY +teamName+" "+ChatColor.RESET+e.getFormat());
            }
            //Otherwise do nothing
        }
    }



}
