package droiidpelaez.westernproject.Economy.Listeners;

import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import droiidpelaez.westernproject.Economy.Wallet;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class OnGoldPickUp implements Listener
{
    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent e)
    {
        Player p = e.getPlayer();
        Wallet wallet = Wallet.getPlayerWallet(p);
        //Ensuring its a gold coin and not something else
            ItemStack pickedUpGold = e.getItem().getItemStack();
            Double namedGold = GlobalUtils.getGoldStrToD(pickedUpGold,p);

            if(pickedUpGold.getType().equals(Material.GOLD_NUGGET)){
                if(namedGold == -1.0){
                    p.sendMessage(ChatColor.GOLD + "Picked up gold!");
                    e.setCancelled(false);
                }
                else if (namedGold > 0.0) {
                    //remove the coin to avoid duping money
                    e.getItem().remove();
                    e.setCancelled(true);
                    //Confirm pick up and give the player their money
                    p.sendMessage(ChatColor.GRAY + "You picked up " + ChatColor.GOLD + namedGold + "g");
                    wallet.addFunds(p, namedGold);
                }
            }
    }
}

