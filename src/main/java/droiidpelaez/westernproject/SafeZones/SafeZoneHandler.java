package droiidpelaez.westernproject.SafeZones;

import droiidpelaez.westernproject.Core;

import java.util.HashMap;

public class SafeZoneHandler {
    private Core plugin;

    public SafeZoneHandler(Core plugin){
        this.plugin = plugin;
    }

    public void loadCords(){
        for(String key : plugin.getConfig().getConfigurationSection("locations").getKeys(false)){
            System.out.println();
            System.out.println(plugin.getConfig().getString("locations."+key+".xpos1"));
            System.out.println(plugin.getConfig().getString("locations."+key+".xpos2"));
            System.out.println(plugin.getConfig().getString("locations."+key+".zpos1"));
            System.out.println(plugin.getConfig().getString("locations."+key+".zpos2"));

            Double xPos1 = Double.parseDouble(plugin.getConfig().getString("locations."+key+".xpos1"));
            Double xPos2 = Double.parseDouble(plugin.getConfig().getString("locations."+key+".xpos2"));
            Double zPos1 = Double.parseDouble(plugin.getConfig().getString("locations."+key+".zpos1"));
            Double zPos2 = Double.parseDouble(plugin.getConfig().getString("locations."+key+".zpos2"));

            SafeZone sf = new SafeZone("test", xPos1, xPos2, zPos1, zPos2);

        }


            System.out.println("Safe zone successfully loaded!");
    }


}
