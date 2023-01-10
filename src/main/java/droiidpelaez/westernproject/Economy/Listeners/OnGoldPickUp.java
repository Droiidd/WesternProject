package droiidpelaez.westernproject.Economy.Listeners;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.GlobalUtils;
import droiidpelaez.westernproject.Economy.Utils.GoldUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class OnGoldPickUp implements Listener {


    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        //Ensuring its a gold coin and not something else
            ItemStack pickedUpGold = e.getItem().getItemStack();
            Double depositGold = getGoldValue(pickedUpGold,p);

            if(depositGold == -1.0){
                p.sendMessage(ChatColor.GOLD + "Picked up gold!");
                e.setCancelled(false);
            }
            else if (depositGold > 0.0) {
                //remove the coin to avoid duping money
                e.getItem().remove();
                e.setCancelled(true);
                //Confirm pick up and give the player their money
                p.sendMessage(ChatColor.GRAY + "You picked up " + ChatColor.GOLD + depositGold + "g");
                BankAccountUtils.updateBalance(p, depositGold);
            }
    }

        public Double getGoldValue(ItemStack goldItem, Player p ){
            String amount = ChatColor.stripColor(goldItem.getItemMeta().getDisplayName());
            String numberOnly= amount.replaceAll("[^0-9]", "");
            Double depositGold  = (GlobalUtils.StrToDNoMsg(numberOnly,p))/10;
            return depositGold;
        }

}

