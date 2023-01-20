package droiidpelaez.westernproject.Roles;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Roles.Commands.Role;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RoleController {
    private final Core plugin;

    private final HashMap<String, Role> roles;
    private final HashMap<String, Role> playerWithRole;
    private final File roleFile;
    private final YamlConfiguration roleConfig;

    public RoleController(Core plugin) {
        this.plugin = plugin;
        roles = new HashMap<>();
        playerWithRole = new HashMap<>();

        roleFile = new File(plugin.getDataFolder(),"roles.yml");

        if(!roleFile.exists()){
            plugin.saveResource("ranks.yml",false);
            //plugin.getDataFolder().mkdir();
        }
        System.out.println("FILE CREATED");

        roleConfig =  YamlConfiguration.loadConfiguration(roleFile);

        Bukkit.getScheduler().runTaskLater(plugin, this ::loadRoles, 20L);
    }

    private void loadRoles(){
        for (String key : plugin.getConfig().getConfigurationSection("roles").getKeys(false)){
            roles.put(key, new Role(
                    key,
                    plugin.getConfig().getString("roles."+key+".name"),
                    plugin.getConfig().getString("roles."+key+".color")
            ));

        }
    }

    public void loadPlayer(Player player){
        Role role = getPlayerFromFile(player);
        if(role == null){
            //handle error
            role = getRole("bandit");
        }
        playerWithRole.put(player.getUniqueId().toString(), role);

    }
    public void savePlayer(Player player){
        Role role = getPlayer(player);
        roleConfig.set("data."+player.getUniqueId().toString(), role.getRole() );

        saveRoleConfig();
    }
    public Role getPlayer(Player player){
            return playerWithRole.get(player.getUniqueId().toString());
    }
    public Role getPlayerFromFile(Player player){
        if(roleConfig.contains("data."+player.getUniqueId().toString())){
            return getRole(roleConfig.getString("data."+player.getUniqueId().toString()));
        }
        return null;
    }
    public Role getRole(String role){
        return roles.get(role);
    }
    public void setPlayerRole(Player player, String role){
        Role selectedRole = getRole(role);
        playerWithRole.put(player.getUniqueId().toString(), selectedRole);
    }
    public void saveRoleConfig(){
        try {
            roleConfig.save(roleFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
