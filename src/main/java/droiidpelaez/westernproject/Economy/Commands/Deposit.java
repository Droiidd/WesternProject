package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.GlobalUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Deposit implements CommandExecutor {
    private static HashMap<String, Double> bankList = BankAccountUtils.getBankList();
    private static HashMap<String, Double> walletList = WalletUtils.getWallets();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length != 1){
                p.sendMessage(ChatColor.RED+ "Incorrect usage please try:");
                p.sendMessage(ChatColor.GRAY+ "/deposit {amount}");
                return true;
            }
            Double amount = GlobalUtils.checkStrToDErrMsg(args[0], p);
            if(amount == -1.0){
                return true;
            }
            if(walletList.containsKey(p.getUniqueId().toString()) != true){
                WalletUtils.createWallet(p);
            }
            if((walletList.get(p.getUniqueId().toString()) - amount) >= 0.0){

                BankAccountUtils.updateBalance(p, amount);
                p.sendMessage(ChatColor.GREEN+"You deposited "+amount+"g");
                WalletUtils.removeMoney(p, amount);
                return true;
            }


        }



        return true;
    }
}
