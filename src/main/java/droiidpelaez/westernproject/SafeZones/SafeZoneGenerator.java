package droiidpelaez.westernproject.SafeZones;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Zombie;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

public class SafeZoneGenerator
{
   public BossBar loadBossBar()
   {
       BossBar bBar = Bukkit.createBossBar(ChatColor.GOLD + "Gold town ",
               BarColor.PURPLE,
               BarStyle.SOLID);
       return bBar;
   }
}
