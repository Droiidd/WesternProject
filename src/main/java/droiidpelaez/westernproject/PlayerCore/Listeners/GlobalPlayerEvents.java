package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Items.HealthItems;
import droiidpelaez.westernproject.SafeZones.Utils.SafeZoneGenerator;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.PlayerCore.Utils.BountyUtils;
import droiidpelaez.westernproject.Roles.Sheriff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Random;


public class GlobalPlayerEvents implements Listener
{
    private final Core plugin;
    //private HashMap<String, Boolean> playersInZoneList = new HashMap<>();
    private int count;
    SafeZoneGenerator gen = new SafeZoneGenerator();
    BossBar test = gen.loadBossBar();
    public GlobalPlayerEvents(Core plugin)
    {
        this.plugin = plugin;
        this.count = 0;
    }
    @EventHandler
    public void dropPlayerHead(PlayerDeathEvent e)
    {
        Player victim = e.getEntity();
        Player killer = victim.getKiller();
        if (killer == null) {
        }
        PlayerCore pCore = PlayerCore.getPlayerCore(victim.getUniqueId().toString());
        if (Sheriff.isSheriff(victim.getUniqueId().toString())) {
            GlobalUtils.dropPlayerHead(victim, 1000.0);
        } else if (pCore.isPlayerWanted()) {
            Integer bounty = pCore.getPlayerBounty() / 2;
            Double headValue = bounty.doubleValue();
            GlobalUtils.dropPlayerHead(victim, headValue);
        } else if (pCore.getPlayerBounty() > 100) {
            Integer bounty = pCore.getPlayerBounty() / 10;
            Double headValue = bounty.doubleValue();
            GlobalUtils.dropPlayerHead(victim, headValue);

        }
    }
    public void startPlayerBleed(Player p, PlayerCore pCore)
    {
                pCore.updateOnlineBleed(p,true);
                BukkitScheduler schedular = Bukkit.getServer().getScheduler();
                int id = schedular.scheduleSyncRepeatingTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        if(!p.isOnline()){
                            pCore.updateBleed(true);
                            Bukkit.getServer().getScheduler().cancelTasks(plugin);
                        }
                        if (count % 2 == 0) {
                            //These are natural seconds
                            if (!pCore.isPlayerBleeding()) {
                                Bukkit.getServer().getScheduler().cancelTasks(plugin);
                            }
                            else{
                                p.damage(1.0);
                            }
                            if(p.isDead()){
                                pCore.updateBleed(false);
                                Bukkit.getServer().getScheduler().cancelTasks(plugin);
                            }

                        }
                        count++;
                    }
                }, 0, 10);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().cancelTask(id);
                    }
                }, 2400);


    }

    @EventHandler
    public void playerBleedEvent(EntityDamageEvent e)
    {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
            if (!pCore.isPlayerBleeding()) {
                int chance = new Random().nextInt(11);
                if (chance % 2 == 0) {
                    p.sendMessage("5050");
                    startPlayerBleed(p,pCore);
                }
            }
            //  Else they are already bleeding
        }
    }
    @EventHandler
    public void breakLegEvent(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof  Player){
            Player p = (Player) e.getEntity();
            PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
            Float fall = p.getFallDistance();
            if(fall > 8){
                if(!pCore.isPlayerCrippled()){
                    pCore.updateOnlineCripple(p, true);
                }
            }
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if (!PlayerCore.hasPlayer(p.getUniqueId().toString())) {
            PlayerCore newPlayer = new PlayerCore(p.getUniqueId().toString(), false, false, false, 0);
            p.sendMessage("New player added!");
        }
        if (Sheriff.isSheriff(p.getUniqueId().toString())) {
            Sheriff pSheriff = Sheriff.getSheriff(p.getUniqueId().toString());
            pSheriff.loadOnlineSheriff(p);
        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if(pCore.isPlayerBleeding()){
            p.sendMessage("THEYRE BLEEDING");
            startPlayerBleed(p,pCore);
        }
        GlobalUtils.loadPlayerStatsDisplay(p);
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e)
    {
        Player p = e.getPlayer();
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if (pCore.isPlayerWanted()) {
            //make talk wanted
        }
        pCore.updateOnlineBounty(p, 10);

    }

    @EventHandler
    public void onBanditAttack(EntityDamageByEntityEvent e)
    {
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
        BountyUtils bUtils = new BountyUtils(plugin);
        if (Sheriff.isSheriff(damager.getUniqueId().toString())) {
            //If damager is sheriff, no consequence
        } else if (Sheriff.isSheriff(victim.getUniqueId().toString())) {
            //if sheriff is victim
            if (!damCore.isPlayerWanted()) {
                bUtils.startWantedTimer(damager);
                damCore.updateOnlineBounty(damager, 500);
                damager.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +500");
            } else {
                damCore.updateOnlineBounty(damager, 50);
                damager.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +50");
            }
        } else if (!vicCore.isPlayerWanted()) {
            if (!damCore.isPlayerWanted()) {
                bUtils.startWantedTimer(damager);
                damCore.updateOnlineBounty(damager, 250);
                damager.sendMessage(ChatColor.GRAY + "Bounty " + ChatColor.RED + " +250");
            } else {
                //damager is already wanted
            }
        }
    }
    @EventHandler
    public void onBanditKill(PlayerDeathEvent e)
    {
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
        } else if (pCore.getPlayerBounty() > 100) {
            // === Dead player had large bounty ===
            pCore.setBounty(p, 0);
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
    public void onWantedDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();
        if (p == null) {

        }
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if (pCore.isPlayerWanted()) {
            Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has fallen.");
            pCore.updateOnlineWanted(p, false);
            Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has fallen.");
        }
        //If player is sheriff -bounty amount
        //If player is bandit -bounty amount
    }

    @EventHandler
    public void onBandageUse(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        ItemStack bandage = HealthItems.getBandage();
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                bandage.getItemMeta().getDisplayName())) {
            if (pCore.isPlayerBleeding()) {
                pCore.updateOnlineBleed(p, false);
                p.getInventory().removeItem(bandage);
            }
        }

    }

    @EventHandler
    public void onSplintUse(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        ItemStack splint = HealthItems.getSplint();
        if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                splint.getItemMeta().getDisplayName()
        )) {
            if (pCore.isPlayerCrippled()) {
                pCore.updateOnlineCripple(p, false);
                p.getInventory().removeItem(splint);
            }
        }
    }
}
//Add event for players hitting other players that aren't wanted.

