package droiidpelaez.westernproject.CoreUtils;

import droiidpelaez.westernproject.Roles.Roles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GlobalUtils {
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
        Player p = Bukkit.getPlayer(playerId);
        if(p == null){
            System.out.println(ChatColor.RED+"PLAYER NOT FOUNDN NOT FOUND");
            return null;
        }
        return p;
    }



}

