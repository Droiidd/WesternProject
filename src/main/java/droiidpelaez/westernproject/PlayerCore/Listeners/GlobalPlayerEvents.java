package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.PlayerCore.Utils.BountyUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class GlobalPlayerEvents implements Listener {
    private final Core plugin;
    Integer count = 0;
    public GlobalPlayerEvents(Core plugin){
        this.plugin = plugin;

    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!PlayerCore.hasPlayer(p.getUniqueId().toString())) {
            PlayerCore newPlayer = new PlayerCore(p.getUniqueId().toString(), false, false, false, 0);
            p.sendMessage("New player added!");
        }
        //pCore.loadJoiningPlayer();


    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if (pCore.isPlayerWanted()) {
            //make talk wanted
        }

        pCore.updateOnlineBounty(p, 10);
        //pCore.updateWanted(true);
        //BountyUtils bUtils = new BountyUtils(plugin);
       // bUtils.startWantedTimer(pCore);


    }

    @EventHandler
    public void onBanditAttack(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            //NOT PLAYER
        }
        if (!(e.getEntity() instanceof Player)) {
            //not player
        }
        Player damager = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();
        PlayerCore damCore = PlayerCore.getPlayerCore(damager.getUniqueId().toString());
        PlayerCore vicCore = PlayerCore.getPlayerCore(victim.getUniqueId().toString());

        if (!vicCore.isPlayerWanted()) {
            if(!damCore.isPlayerWanted()){
                BountyUtils bUtils = new BountyUtils(plugin);
                bUtils.startWantedTimer(damager);
            }

        }

    }

    @EventHandler
    public void onBanditKill(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p == null) {
        }
        Player killer = p.getKiller();
        if (killer == null) {
        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        PlayerCore killCore = PlayerCore.getPlayerCore(killer.getUniqueId().toString());
        if (pCore.isPlayerWanted()) {
            pCore.updateOnlineWanted(p,false);
        } else {
            //Was killed by wanted player
            killCore.updateOnlineBounty(p,500);
        }
    }

    @EventHandler
    public void wantedDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p == null) {
        //No player
        }
        p.setPlayerListName( p.getDisplayName());
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if (pCore.isPlayerWanted()) {
            pCore.updateOnlineWanted(p,false);
            for (Player target : Bukkit.getOnlinePlayers()) {
                target.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has fallen.");
            }
        }
    }

    //Add event for players hitting other players that aren't wanted.
}
