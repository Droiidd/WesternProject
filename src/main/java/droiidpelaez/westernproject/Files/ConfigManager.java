package droiidpelaez.westernproject.Files;

import droiidpelaez.westernproject.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private Core plugin = Core.getPlugin(Core.class);

    //Files & config here
    public FileConfiguration playerCFG;
    public File playerFile;

    public void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        playerFile = new File(plugin.getDataFolder(), "players.yml");
        if(!playerFile.exists()){
            try{
                playerFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        playerCFG = YamlConfiguration.loadConfiguration(playerFile);

    }

    public FileConfiguration getPlayerCFG() {
        return playerCFG;
    }

    public void savePlayers(){
        try{
            playerCFG.save(playerFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadPlayers(){
        playerCFG = YamlConfiguration.loadConfiguration(playerFile);
    }
}
