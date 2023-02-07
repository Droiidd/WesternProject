package droiidpelaez.westernproject.SafeZones;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Zombie;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

public class SafeZoneGenerator {

    HashMap<String, Double> pos1CordList = new HashMap<>();
    HashMap<String, Double> pos2CordList = new HashMap<>();

//    public void createSafeZone(String zoneName ,Integer pos1, Integer pos2){
//        if(!pos1CordList.containsKey(zoneName) && !pos2CordList.containsKey(zoneName)){
//           pos1CordList.put(zoneName, pos1);
//           pos2CordList.put(zoneName,pos2);
//        }
//    }
    public void setPos1(String zoneName, Double pos1)
    {
        if(!pos1CordList.containsKey(zoneName)){
            pos1CordList.put(zoneName, pos1);
        }
        else{
            pos1CordList.replace(zoneName, pos1);
        }
    }
    public void setPos2(String zoneName, Double pos2)
    {
        if(!pos2CordList.containsKey(zoneName)){
            pos2CordList.put(zoneName, pos2);
        }
        else{
            pos2CordList.replace(zoneName, pos2);
        }
    }

    public Double getPos1(String zoneName)
    {
        if(!pos1CordList.containsKey(zoneName)){;
            return null;
        }
        return pos1CordList.get(zoneName);
    }
    public Double getPos2(String zoneName)
    {
        if(!pos2CordList.containsKey(zoneName)){;
            return null;
        }
        return pos2CordList.get(zoneName);
    }

    public boolean isZoneInitialized(String zoneName){
        if(pos1CordList.containsKey(zoneName) && pos2CordList.containsKey(zoneName)){
            return true;
        }
        return false;
    }



}
