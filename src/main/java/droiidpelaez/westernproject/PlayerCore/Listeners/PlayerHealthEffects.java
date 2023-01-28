package droiidpelaez.westernproject.PlayerCore.Listeners;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public class PlayerHealthEffects implements Listener {

    private Core plugin;
    private int count;

    public PlayerHealthEffects(Core plugin) {
        this.plugin = plugin;
        count = 0;
    }

    @EventHandler
    public void playerBleedEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
            if (!pCore.isPlayerBleeding()) {
                int chance = new Random().nextInt(11);
                if (chance % 2 == 0) {
                    p.sendMessage("5050");
                    pCore.updateOnlineBleed(p,true);
                    BukkitScheduler schedular = Bukkit.getServer().getScheduler();
                    int id = schedular.scheduleSyncRepeatingTask(plugin, new Runnable() {
                        @Override
                        public void run() {
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
            }
        }
    }

    @EventHandler
    public void breakLegEvent(EntityDamageEvent e){
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
}
