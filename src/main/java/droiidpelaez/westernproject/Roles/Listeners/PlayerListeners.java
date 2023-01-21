package droiidpelaez.westernproject.Roles.Listeners;

import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.Roles.Commands.Role;
import droiidpelaez.westernproject.Roles.RoleController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {
    private final RoleController roleController;

    public PlayerListeners(RoleController roleController) {
        this.roleController = roleController;
    }

    @EventHandler
    public void  onJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage("GDJHBNFKJK");
        Player p = e.getPlayer();
        p.sendMessage("p joined");
        roleController.loadPlayer(p);
        p.sendMessage("p loaded");

    }
    @EventHandler
    public void  onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        roleController.savePlayer(p);
    }
    @EventHandler
    public void onChat(PlayerChatEvent e){
        Player p = e.getPlayer();

        Role role = roleController.getPlayer(p);
        p.sendMessage("Chat recognized");
        e.setMessage(GlobalUtils.color(role.getColor()) +e.getMessage());

    }



        }
