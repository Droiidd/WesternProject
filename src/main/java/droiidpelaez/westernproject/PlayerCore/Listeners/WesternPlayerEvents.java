package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class WesternPlayerEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!PlayerCore.hasPlayer(p)){
            PlayerCore newPlayer = new PlayerCore(p, false, false, false, 0.0);
            p.sendMessage("New player added!");
        }
    }
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e){
        Player p = e.getPlayer();

        if(!PlayerCore.hasPlayer(p)){
            p.sendMessage("no no no.");
        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p);
        if(pCore.getPlayerWantedStat()){
            //make talk wanted
        }

        pCore.updateBounty(10.0);

    }
    @EventHandler
    //Add event for players hitting other players that aren't wanted.
}
