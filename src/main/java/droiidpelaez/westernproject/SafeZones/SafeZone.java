package droiidpelaez.westernproject.SafeZones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SafeZone {
    private String name;
    private double xPos1;
    private double xPos2;
    private double zPos1;
    private double zPos2;
    private List<SafeZone> zoneList = new ArrayList<>();
    private static HashMap<String, SafeZone> zoneMap = new HashMap<>();

    public SafeZone(String name, double xPos1, double xPos2, double zPos1, double zPos2){
        this.name = name;
        this.xPos1 = xPos1;
        this.xPos2 = xPos2;
        this.zPos1 = zPos1;
        this.zPos2 = zPos2;
        zoneList.add(this);
        zoneMap.put(name, this);
    }

    public static SafeZone getSafeZone(String name){
        if(zoneMap.containsKey(name)){
            return zoneMap.get(name);
        }
        return null;
    }
    public void setxPos1(double pos1){
        this.xPos1 = pos1;
    }
    public void setxPos2(double pos2){
        this.xPos2 = pos2;
    }
    public double getxPos1(){
        return xPos1;
    }
    public double getxPos2(){
        return xPos2;
    }
    public double getzPos1(){
        return zPos1;
    }
    public double getzPos2(){
        return zPos2;
    }


}
