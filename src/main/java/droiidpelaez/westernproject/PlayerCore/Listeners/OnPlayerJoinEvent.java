package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(p.hasPlayedBefore()){
            PlayerCore newPlayer = new PlayerCore(p, false, false, false, 0.0);
            p.sendMessage("New player added!");
        }
    }
}
