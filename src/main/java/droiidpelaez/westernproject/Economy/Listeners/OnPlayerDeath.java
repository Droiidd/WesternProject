package droiidpelaez.westernproject.Economy.Listeners;

import droiidpelaez.westernproject.Economy.Utils.GoldUtils;
import droiidpelaez.westernproject.Economy.Wallet;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        //Important location: where we'll spawn the gold coin
        Location deathPoint = p.getLocation();
        //Double of your bank, create a gold coin of that value and drop it at deathpoint
        Double lostMoney = Wallet.getPlayerFunds(p);

        Wallet.removeMoney(p, lostMoney);

        ItemStack gold = GoldUtils.getNewCoin(lostMoney);
        deathPoint.getBlock().getWorld().dropItem(deathPoint, gold);



    }
}