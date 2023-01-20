package droiidpelaez.westernproject.Economy.Listeners;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.GoldUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import org.bukkit.Location;
import org.bukkit.block.data.type.Wall;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class OnPlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        //Important location: where we'll spawn the gold coin
        Location deathPoint = p.getLocation();
        //Double of your bank, create a gold coin of that value and drop it at deathpoint
        Double lostMoney = WalletUtils.getPlayerFunds(p);

        WalletUtils.removeMoney(p, lostMoney);

        ItemStack gold = GoldUtils.getNewCoin(lostMoney);
        deathPoint.getBlock().getWorld().dropItem(deathPoint, gold);



    }
}