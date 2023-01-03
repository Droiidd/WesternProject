package droiidpelaez.westernproject.Economy.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GlobalUtils {

        public static Double checkPlayerStrToD(String s, Player p){
            try{
                Double amount = Double.parseDouble(s);
                return amount;
            }catch(NumberFormatException e){
                p.sendMessage(ChatColor.RED+ "Incorrect usage "+ ChatColor.GRAY+"please enter a valid amount.");
            }
            return 0.0;
        }
        public static Double strToD(String s){
            try{
                Double amount = Double.parseDouble(s);
                return amount;
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
            return 0.0;
        }



}

