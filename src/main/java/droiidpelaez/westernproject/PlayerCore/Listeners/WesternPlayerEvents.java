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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;


public class WesternPlayerEvents implements Listener {
    private final Core plugin;
    Integer count = 0;
    public WesternPlayerEvents(Core plugin){
        this.plugin = plugin;

    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!PlayerCore.hasPlayer(p)) {
            PlayerCore newPlayer = new PlayerCore(p, false, false, false, 0.0);
            p.sendMessage("New player added!");
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        Player p = e.getPlayer();

        if (!PlayerCore.hasPlayer(p)) {
            p.sendMessage("no no no.");
        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p);
        if (pCore.isPlayerWanted()) {
            //make talk wanted
        }

        pCore.updateBounty(10.0);
        pCore.updateWanted(true);
        BountyUtils bUtils = new BountyUtils(plugin);
        bUtils.startWantedTimer(pCore);


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
        PlayerCore damCore = PlayerCore.getPlayerCore(damager);
        PlayerCore vicCore = PlayerCore.getPlayerCore(victim);

        if (!vicCore.isPlayerWanted()) {
            if(!damCore.isPlayerWanted()){
                BountyUtils bUtils = new BountyUtils(plugin);
                bUtils.startWantedTimer(damCore);
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
        PlayerCore pCore = PlayerCore.getPlayerCore(p);
        PlayerCore killCore = PlayerCore.getPlayerCore(killer);
        if (pCore.isPlayerWanted()) {
            //DROP PLAYERS BOUNTY HERE
        } else {
            //Was killed by wanted player
            killCore.updateBounty(500.0);
        }
    }

    @EventHandler
    public void wantedDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p == null) {
        //No player
        }
        p.setPlayerListName( p.getDisplayName());
        PlayerCore pCore = PlayerCore.getPlayerCore(p);
        if (pCore.isPlayerWanted()) {
            pCore.updateWanted(false);
            for (Player target : Bukkit.getOnlinePlayers()) {
                target.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has fallen.");
            }
        }
    }

    //Add event for players hitting other players that aren't wanted.
}
