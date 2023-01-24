package droiidpelaez.westernproject.CoreUtils;

import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Roles.Roles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GlobalUtils {
    public static void loadPidScoreboard(String pId){
        Player target = GlobalUtils.getPlayerFromString(pId);
        if(target != null){
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadBanditScoreboard(target);
        }
        System.out.println("Oops - team add player");
    }
        public static Double checkStrToDErrMsg(String s, Player p){
            try{
                Double amount = Double.parseDouble(s);
                return amount;
            }catch(NumberFormatException e){
                p.sendMessage(ChatColor.RED+ "Incorrect usage "+ ChatColor.GRAY+"please enter a valid amount.");
            }
            return -1.0;
        }
    public static Double StrToDNoMsg(String s, Player p){
        try{
            Double amount = Double.parseDouble(s);
            return amount;
        }catch(NumberFormatException e){
            return -1.0;
        }

    }
    public static Double getGoldStrToD(ItemStack goldItem, Player p ){
        String amount = ChatColor.stripColor(goldItem.getItemMeta().getDisplayName());
        String numberOnly= amount.replaceAll("[^0-9]", "");
        Double depositGold  = (GlobalUtils.StrToDNoMsg(numberOnly,p))/10;
        return depositGold;
    }
    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    public static Player getPlayerFromString(String uuid){
        UUID playerId = UUID.fromString(uuid);
        Player p = (Player) Bukkit.getOfflinePlayer(playerId);
        if(p == null){
            System.out.println(ChatColor.RED+"PLAYER NOT FOUNDN NOT FOUND");
            return null;
        }
        return p;
    }



}

