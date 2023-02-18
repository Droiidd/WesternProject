package droiidpelaez.westernproject.UtilCore;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Items.Listeners.BlockBreaking;
import droiidpelaez.westernproject.PlayerCore.Listeners.AllChatEvents;
import droiidpelaez.westernproject.PlayerCore.Listeners.GlobalPlayerEvents;
import droiidpelaez.westernproject.PlayerCore.Listeners.PlayerHealthEffects;
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
        plugin.getServer().getPluginManager().registerEvents(new PlayerHealthEffects(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GlobalPlayerEvents(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new AllChatEvents(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SafeZoneListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockBreaking(plugin), plugin);
    }
}

