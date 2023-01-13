package droiidpelaez.westernproject.Files;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomConfig {
    private static File file;
    private static FileConfiguration customFile;
    private static String name;

    public CustomConfig(String name){
        this.name = name;
    }
    public  void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("WesternProject").getDataFolder(), name+".yml");
        if(!file.exists()){
            try{file.createNewFile();
            }catch (IOException e){
                System.out.println("Could not save file.");}
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getCustomFile(){
        return customFile;
    }

    public void save(){
        try{
            customFile.save(file);}
        catch (IOException e){
            System.out.println("Could not save file.");}
    }
    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }


    // ====== Important methods ======

}
