package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Teams.Listeners.OnPlayerChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!PlayerCore.hasPlayer(p)){
            PlayerCore newPlayer = new PlayerCore(p, false, false, false, 0.0);
            p.sendMessage("New player added!");
        }
        else{
            //update player info here
            p.sendMessage("hi!");
        }
    }
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        p.sendMessage("Shut up! Bounty +10");
        if(!PlayerCore.hasPlayer(p)){
            p.sendMessage("no no no.");
        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p);
        pCore.updateBounty(10.0);

        p.sendMessage("Bounty: " + pCore.getPlayerBounty(p));

    }
}
