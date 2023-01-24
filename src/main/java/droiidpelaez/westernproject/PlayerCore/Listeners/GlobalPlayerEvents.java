package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.CoreUtils.GlobalUtils;
import droiidpelaez.westernproject.CoreUtils.ScoreboardUtils;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.PlayerCore.Utils.BountyUtils;
import droiidpelaez.westernproject.Roles.Sheriff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;


public class GlobalPlayerEvents implements Listener {
    private final Core plugin;
    Integer count = 0;

    public GlobalPlayerEvents(Core plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!PlayerCore.hasPlayer(p.getUniqueId().toString())) {
            PlayerCore newPlayer = new PlayerCore(p.getUniqueId().toString(), false, false, false, 0);
            p.sendMessage("New player added!");
        }
        GlobalUtils.loadPlayerNameTags(p);

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
        if (Sheriff.isSheriff(damager.getUniqueId().toString())) {

        }
        else if (Sheriff.isSheriff(victim.getUniqueId().toString())) {
            if (!damCore.isPlayerWanted()) {
                BountyUtils bUtils = new BountyUtils(plugin);
                bUtils.startWantedTimer(damager);
                damCore.updateOnlineBounty(damager, 500);
                damager.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +500");
            } else {
                damCore.updateOnlineBounty(damager, 50);
                damager.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +50");
            }
        }
        else if (!vicCore.isPlayerWanted()) {
            if (!damCore.isPlayerWanted()) {
                BountyUtils bUtils = new BountyUtils(plugin);
                bUtils.startWantedTimer(damager);
                damCore.updateOnlineBounty(damager, 250);
                damager.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +250");
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
        if (Sheriff.isSheriff(killer.getUniqueId().toString())) {
            //Check if player was wanted?
        } else if (pCore.isPlayerWanted()) {
            // === Dead player was wanted ===
            pCore.updateOnlineWanted(p, false);
            ScoreboardUtils sbUtils = new ScoreboardUtils();
            sbUtils.loadPlayerScoreboard(p);
            Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has fallen.");
            p.setPlayerListName(p.getDisplayName());

        } else if (Sheriff.isSheriff(p.getUniqueId().toString())) {
            // === Dead player was a sheriff
            killCore.updateOnlineBounty(killer, 1000);
            killer.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +1000");
        } else {
            // === Dead player was nice ===
            killCore.updateOnlineBounty(killer, 500);
            killer.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +500");
        }
    }

    @EventHandler
    public void onWantedDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        if(p == null){

        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if(pCore.isPlayerWanted()){
            pCore.updateOnlineWanted(p, false);
            ScoreboardUtils sbUtils = new ScoreboardUtils();
            sbUtils.loadPlayerScoreboard(p);
            Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has fallen.");
            p.setPlayerListName(p.getDisplayName());
        }
        //If player is sheriff -bounty amount
        //If player is bandit -bounty amount

    }



}


//Add event for players hitting other players that aren't wanted.

