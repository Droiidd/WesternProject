package droiidpelaez.westernproject.SafeZones;

import droiidpelaez.westernproject.Core;

import java.util.HashMap;

public class SafeZoneHandler {
    private Core plugin;

    public SafeZoneHandler(Core plugin){
        this.plugin = plugin;
    }

    public void loadCords(){
        if(plugin.getConfig().contains("test")){
            Double xPos1 = (Double) plugin.getConfig().get("xPos1");
            Double xPos2 = (Double) plugin.getConfig().get("xPos2");
            Double zPos1 = (Double) plugin.getConfig().get("zPos1");
            Double zPos2 = (Double) plugin.getConfig().get("zPos2");
            SafeZone sf = new SafeZone("test", xPos1, xPos2, zPos1, zPos2);
            System.out.println("Safe zone successfully loaded!");
        }
    }


}
