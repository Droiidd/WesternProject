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
    public void onPlayerPickUp(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        //Creating a gold Item to compare the picked up item to
        ItemStack testGold = GoldUtils.getGoldItem(0.0);

        //Ensuring its a gold coin and not something else
        if(e.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Gold Nugget")){
            e.setCancelled(false);
            p.sendMessage(ChatColor.GOLD+ "Picked up gold.");
        }
        else if(e.getItem().getItemStack().getType().equals(testGold.getType())){
            //remove the coin to avoid duping money
            e.getItem().remove();
            e.setCancelled(true);

            //Clones the coin to avoid damage, then converts its name to a readable double. ()
            ItemStack checkIfGold = e.getItem().getItemStack();
            String amount = ChatColor.stripColor(checkIfGold.getItemMeta().getDisplayName());
            String numberOnly= amount.replaceAll("[^0-9]", "");
            Double depositGold  = (GlobalUtils.checkPlayerStrToD(numberOnly,p))/10;
            if(depositGold == 0){
                e.getPlayer().getInventory().addItem(GoldUtils.getNormNugget());
                return;
            }

            //Confirm pick up and give the player their money
            p.sendMessage(ChatColor.GRAY+ "You picked up "+ChatColor.GOLD +depositGold+"g");
            BankAccountUtils.updateBalance(p, depositGold);

        }



    }



}

