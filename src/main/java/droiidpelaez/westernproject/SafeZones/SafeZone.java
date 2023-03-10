package droiidpelaez.westernproject.SafeZones;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SafeZone
{
    private HashMap<String, Boolean> playersInZoneList = new HashMap<>();
    private String name;
    private Double xPos1;
    private Double xPos2;
    private Double zPos1;
    private Double zPos2;
    private List<SafeZone> zoneList = new ArrayList<>();
    private static HashMap<String, SafeZone> zoneMap = new HashMap<>();

    public SafeZone(String name, Double xPos1, Double xPos2, Double zPos1, Double zPos2)
    {
        this.name = name;
        this.xPos1 = xPos1;
        this.xPos2 = xPos2;
        this.zPos1 = zPos1;
        this.zPos2 = zPos2;
        zoneList.add(this);
        zoneMap.put(name, this);
    }
    public boolean isPlayerInZone(Player p)
    {
        Double minX;
        Double maxX;
        Double minZ;
        Double maxZ;
        double tempPlayX = p.getLocation().getX();
        double tempPlayZ = p.getLocation().getZ();
        Double playerX = new Double(tempPlayX);
        Double playerZ = new Double(tempPlayZ);


        if (xPos1 < xPos2) {
            minX = xPos1;
            maxX = xPos2;
        } else {
            minX = xPos2;
            maxX = xPos1;
        }
        if (zPos1 < zPos2) {
            minZ = zPos1;
            maxZ = zPos2;
        } else {
            minZ = zPos2;
            maxZ = zPos1;
        }
        //PLAYER IS WITHIN THE ZONE
        return (playerX > minX && playerX < maxX) && (playerZ > minZ && playerZ < maxZ);
    }
    public static SafeZone getSafeZone(String name)
    {
        if(zoneMap.containsKey(name)){
            return zoneMap.get(name);
        }
        return null;
    }
    public void setxPos1(Double pos1)
    {
        this.xPos1 = pos1;
    }
    public void setxPos2(Double pos2)
    {
        this.xPos2 = pos2;
    }
    public Double getxPos1()
    {
        return xPos1;
    }
    public Double getxPos2()
    {
        return xPos2;
    }
    public Double getzPos1()
    {
        return zPos1;
    }
    public Double getzPos2()
    {
        return zPos2;
    }


}
