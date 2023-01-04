package droiidpelaez.westernproject;

import droiidpelaez.westernproject.Economy.Commands.AdminCommands;
import droiidpelaez.westernproject.Economy.Commands.CheckBalance;
import droiidpelaez.westernproject.Economy.Commands.DropGold;
import droiidpelaez.westernproject.Economy.Commands.GiveGold;
import droiidpelaez.westernproject.Economy.Listeners.OnGoldPickUp;
import droiidpelaez.westernproject.Economy.Listeners.OnPlayerDeath;
import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Teams.Commands.TeamCommands;
import droiidpelaez.westernproject.Teams.Listeners.OnPlayerChat;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {
    private static HashMap<String, Double> map = BankAccountUtils.listAllBanks();

    @Override
    public void onEnable() {
        // === COMMANDS ===
        getCommand("balance").setExecutor(new CheckBalance());
        getCommand("eco").setExecutor(new AdminCommands());
        getCommand("giveGold").setExecutor(new GiveGold());
        getCommand("drop").setExecutor(new DropGold());

        getCommand("team").setExecutor(new TeamCommands());

        // === EVENTS ===
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnGoldPickUp(), this);

        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);

        // === SAVING ===
        this.saveDefaultConfig();
        if(this.getConfig().contains("data")){
            this.restoreFile();
        }


    }

    public void saveFile(){
        for(Map.Entry<String, Double> entry : map.entrySet()){
            this.getConfig().set("data."+entry.getKey(), entry.getValue());
        }
        this.saveConfig();

    }
    public void restoreFile(){
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) this.getConfig().get("data."+key);
            map.put(key, account);
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(!map.isEmpty()){
            this.saveFile();
        }
    }
}
