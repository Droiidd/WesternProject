package droiidpelaez.westernproject.Economy.Utils;

import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Wallet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AccountController
{
    private String geologistName = ChatColor.BLUE+""+ ChatColor.BOLD+"Geologist";
    private String bankerName = ChatColor.GOLD+""+ChatColor.BOLD+"Banker";

    public boolean submitWithdrawal(Player p, Double amount)
    {
        Wallet.updateBalance(p, amount);
        Bank.removeFunds(p, amount);
        p.sendMessage(bankerName+ ChatColor.GRAY+ ": Withdrew "+amount+ChatColor.GOLD+"g");
        return false;
    }



}
