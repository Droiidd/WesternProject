package droiidpelaez.westernproject.SafeZones.Listeners;

import droiidpelaez.westernproject.SafeZones.SafeZone;
import droiidpelaez.westernproject.SafeZones.Utils.SafeZoneGenerator;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class SafeZoneListener implements Listener
{
    private SafeZoneGenerator gen = new SafeZoneGenerator();
    private BossBar test = gen.loadBossBar();
    private HashMap<String, Boolean> playerZoneMap = new HashMap<>();
    @EventHandler
    public void attackInTown(EntityDamageEvent e)
    {
        SafeZone sf = SafeZone.getSafeZone("test");
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(sf.isPlayerInZone(p)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void playerEnterZone(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();

        SafeZone sf = SafeZone.getSafeZone("test");

        if(sf.isPlayerInZone(p)) {
            //Player is in the town
            //Mess with player in town attributes here
            if(!playerZoneMap.containsKey(p.getUniqueId().toString())){
                playerZoneMap.put(p.getUniqueId().toString(), true);
                test.setProgress(1);
                test.addPlayer(p);
                test.setVisible(true);
                p.sendTitle("Welcome to gold town","",8,15,8);
            }
            else if(!playerZoneMap.get(p.getUniqueId().toString())){
                playerZoneMap.replace(p.getUniqueId().toString(), true);
                test.setProgress(1);
                test.addPlayer(p);
                test.setVisible(true);
                p.setInvisible(true);
                p.sendTitle("Welcome to gold town","",8,15,8);
            }
            //PLAYER IS INSIDE ZONE
        }
        else{
            if(playerZoneMap.get(p.getUniqueId().toString())){
                playerZoneMap.replace(p.getUniqueId().toString(), false);
                p.sendTitle("Now leaving gold town","",8,15,8);
            }
            test.removePlayer(p);
            test.setVisible(false);
            p.setInvisible(false);

        }
    }

}
