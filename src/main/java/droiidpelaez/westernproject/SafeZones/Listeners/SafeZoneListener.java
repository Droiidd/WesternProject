package droiidpelaez.westernproject.SafeZones.Listeners;

import droiidpelaez.westernproject.SafeZones.SafeZone;
import droiidpelaez.westernproject.SafeZones.SafeZoneGenerator;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class SafeZoneListener implements Listener {

    private HashMap<String, Boolean> playersInZoneList = new HashMap<>();
    SafeZoneGenerator gen = new SafeZoneGenerator();
    BossBar test = gen.loadBossBar();
    Double minX;
    Double maxX;
    Double minZ;
    Double maxZ;

    public void playerLeavingZone(Player p) {
        if (!playersInZoneList.containsKey(p.getUniqueId().toString())) {
            //Player is not in loaded in ANY zone
        }
        if (!playersInZoneList.get(p.getUniqueId().toString())) {
            //Do nothing because its already false and should not display
            p.sendMessage("not in zone");
        } else {
            test.removePlayer(p);
            test.setVisible(false);
            playersInZoneList.replace(p.getUniqueId().toString(), false);
            p.sendMessage("Leaving a town: " + playersInZoneList.get(p.getUniqueId().toString()));

        }
    }

    public void playerEnteringZone(Player p) {

    }


    @EventHandler
    public void playerEnterZone(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        SafeZone sf = SafeZone.getSafeZone("test");
        Double xpos1 = sf.getxPos1();
        Double xpos2 = sf.getxPos2();
        Double zpos1 = sf.getzPos1();
        Double zpos2 = sf.getzPos2();

        double tempPlayX = p.getLocation().getX();
        double tempPlayZ = p.getLocation().getZ();
        Double playerX = new Double(tempPlayX);
        Double playerZ = new Double(tempPlayZ);


        if (xpos1 < xpos2) {
            minX = xpos1;
            maxX = xpos2;
        } else {
            minX = xpos2;
            maxX = xpos1;
        }
        if (zpos1 < zpos2) {
            minZ = zpos1;
            maxZ = zpos2;
        } else {
            minZ = zpos2;
            maxZ = zpos1;
        }

        if (playerX > minX && playerX < maxX) {
            if (playerZ > minZ && playerZ < maxZ) {

                if (!playersInZoneList.containsKey(p.getUniqueId().toString())) {
                    playersInZoneList.put(p.getUniqueId().toString(), true);
                    //Add player to list
                }
                if (!playersInZoneList.get(p.getUniqueId().toString())) {
                    playersInZoneList.replace(p.getUniqueId().toString(), true);
                    p.sendMessage("First time walking in: " + playersInZoneList.get(p.getUniqueId().toString()));

                    test.setProgress(1);
                    test.addPlayer(p);
                    test.setVisible(true);

                } else {
                    p.sendMessage("Walking around: " + playersInZoneList.get(p.getUniqueId().toString()));
                }
            } else {
                playerLeavingZone(p);
            }
            //Player is not inside the parameters
        } else {
            playerLeavingZone(p);
        }

    }

}
