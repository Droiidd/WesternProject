package droiidpelaez.westernproject.UtilCore;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Items.Listeners.BlockBreaking;
import droiidpelaez.westernproject.Items.Listeners.Foraging;
import droiidpelaez.westernproject.Items.Listeners.Harvesting;
import droiidpelaez.westernproject.NPC.Listeners.NPCInteractions;
import droiidpelaez.westernproject.NPC.Listeners.NPCguiController;
import droiidpelaez.westernproject.PlayerCore.Listeners.AllChatEvents;
import droiidpelaez.westernproject.PlayerCore.Listeners.GlobalPlayerEvents;
import droiidpelaez.westernproject.SafeZones.Listeners.SafeZoneListener;

public class EventRegister
{
    private Core plugin;
    public EventRegister(Core plugin)
    {
        this.plugin = plugin;
    }
    public void regAllEvents()
    {
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerDeath(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnGoldPickUp(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GlobalPlayerEvents(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new AllChatEvents(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SafeZoneListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockBreaking(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new Foraging(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new Harvesting(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new NPCInteractions(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new NPCguiController(plugin), plugin);
    }
}

