package droiidpelaez.westernproject.SafeZones.Listeners;

import droiidpelaez.westernproject.SafeZones.SafeZone;
import droiidpelaez.westernproject.SafeZones.SafeZoneGenerator;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.List;

public class SafeZoneListener implements Listener {

    private SafeZoneGenerator gen = new SafeZoneGenerator();
    private BossBar test = gen.loadBossBar();
    private HashMap<String, Boolean> playerZoneMap = new HashMap<>();
    public void playerEnteringZone(Player p) {

    }


    @EventHandler
    public void playerEnterZone(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        SafeZone sf = SafeZone.getSafeZone("test");

        if(sf.isPlayerInZone(p)) {
            //Player is in the town
            //Mess with player in town attributes here
            if(!playerZoneMap.containsKey(p.getUniqueId().toString())){
                p.sendMessage("First time in zone");
                playerZoneMap.put(p.getUniqueId().toString(), true);
                test.setProgress(1);
                test.addPlayer(p);
                test.setVisible(true);
                p.setInvisible(true);
            }
            else if(!playerZoneMap.get(p.getUniqueId().toString())){
                p.sendMessage("...");
                playerZoneMap.replace(p.getUniqueId().toString(), true);
                test.setProgress(1);
                test.addPlayer(p);
                test.setVisible(true);
                p.setInvisible(true);
            }

        }
        else{
            test.removePlayer(p);
            test.setVisible(false);
            p.setInvisible(false);
        }




    }

}
