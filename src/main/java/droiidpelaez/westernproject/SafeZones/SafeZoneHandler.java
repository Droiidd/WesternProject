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
            double xPos1 = (double) plugin.getConfig().get("xPos1");
            double xPos2 = (double) plugin.getConfig().get("xPos2");
            double zPos1 = (double) plugin.getConfig().get("zPos1");
            double zPos2 = (double) plugin.getConfig().get("zPos2");
            SafeZone sf = new SafeZone("test", xPos1, xPos2, zPos1, zPos2);
            System.out.println("Safe zone successfully loaded!");
        }
    }


}
