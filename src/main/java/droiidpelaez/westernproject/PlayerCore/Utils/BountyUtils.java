package droiidpelaez.westernproject.PlayerCore.Utils;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

public class BountyUtils {
    private HashMap<String, Long> cooldown = new HashMap<>();
    private final Core plugin;
    private Integer count;
    private Integer timerSeconds;
    private Integer timerMinutes;

    public BountyUtils(Core plugin){
        this.plugin = plugin;
        count = 0;
    }

    public void startWantedTimer(Player p){
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        BukkitScheduler schedular = Bukkit.getServer().getScheduler();
        timerSeconds = 0;
        timerMinutes = 2;
        ScoreboardUtils sbUtils = new ScoreboardUtils();
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wanted " + ChatColor.GRAY + p.getDisplayName() + " has gone rogue!");
        pCore.updateOnlineWanted(p,true);



        cooldown.put(p.getUniqueId().toString(), System.currentTimeMillis() + (10 * 1000));


        int id = schedular.scheduleSyncRepeatingTask(plugin, new Runnable(){
            @Override
            public void run(){
                if(count % 2 == 0){
                    if(timerSeconds == -1){
                        timerSeconds = 59;
                        timerMinutes--;

                    }
                    if(cooldown.containsKey(p.getUniqueId().toString())){
                        if(cooldown.get(p.getUniqueId().toString() )>System.currentTimeMillis()){
                            Long timeLeft =  cooldown.get(p.getUniqueId().toString()) - System.currentTimeMillis()/1000;
                            Integer t = timeLeft.intValue();
                            //Still have time left in the cooldown

                            p.sendMessage("time left "+t);
                        }
                        else{
                            Bukkit.getServer().getScheduler().cancelTasks(plugin);
                        }
                    }
                    //Bukkit.broadcastMessage(timerMinutes.toString()+" "+ timerSeconds.toString());
                    sbUtils.loadWantedScoreboard(p, timerMinutes, timerSeconds);
                    timerSeconds--;


                }
                count++;

            }


        },0, 10);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                p.sendMessage("Enough.");
                pCore.updateOnlineWanted(p,false);
                if(!pCore.isPlayerWanted()){
                    Bukkit.getScheduler().cancelTask(id);
                }

            }
        },  2400);

    }
}
