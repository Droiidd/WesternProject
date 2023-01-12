package droiidpelaez.westernproject.Economy.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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



}

