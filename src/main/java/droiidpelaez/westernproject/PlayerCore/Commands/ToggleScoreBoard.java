package droiidpelaez.westernproject.PlayerCore.Commands;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ToggleScoreBoard implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard sb = manager.getNewScoreboard();
            //Title of scoreboard
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GOLD+"Contract Info");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);


            if(!BankAccountUtils.hasAccount(p)){
                BankAccountUtils.createBankAccount(p);
            }
            if(!WalletUtils.hasAccount(p)){
                WalletUtils.createWallet(p);
            }
            Score bankDisplay = objective.getScore(ChatColor.GREEN+"Bank: "+ChatColor.GRAY+ BankAccountUtils.getPlayerFunds(p).toString()+"g");
            Score walletDisplay = objective.getScore(ChatColor.GREEN+"Wallet: "+ChatColor.GRAY+ WalletUtils.getPlayerFunds(p).toString()+"g");
            Score separatorLine = objective.getScore(ChatColor.GRAY+"---------------");
            separatorLine.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine.setScore(4);
            if(!PlayerCore.hasPlayer(p)){
                //error
            }
            //PlayerCore targetPlayer = PlayerCore.getCore()


            p.setScoreboard(sb);





        }




        return true;
    }
}
