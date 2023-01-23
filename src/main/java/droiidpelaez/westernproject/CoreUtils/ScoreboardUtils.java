package droiidpelaez.westernproject.CoreUtils;

import droiidpelaez.westernproject.Economy.Utils.BankAccountUtils;
import droiidpelaez.westernproject.Economy.Utils.WalletUtils;
import droiidpelaez.westernproject.Teams.PlayerCore.PlayerCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardUtils {

    public static void loadPlayerScoreboard(PlayerCore pCore){
        Player p = pCore.getPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        //Title of scoreboard
        Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GOLD+"Contract Info");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score separatorLine = objective.getScore(ChatColor.GRAY+"---------------");
        Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------------");
        Score separatorLine3 = objective.getScore(ChatColor.GRAY+"---------------");
        Score bankDisplay = objective.getScore(ChatColor.GREEN+"Bank: "+ChatColor.GRAY+ BankAccountUtils.getPlayerFunds(p).toString()+"g");
        Score walletDisplay = objective.getScore(ChatColor.GREEN+"Wallet: "+ChatColor.GRAY+ WalletUtils.getPlayerFunds(p).toString()+"g");


        separatorLine.setScore(7);
        bankDisplay.setScore(6);
        walletDisplay.setScore(5);
        separatorLine2.setScore(4);

        if(!PlayerCore.hasPlayer(p)){
            //error
        }
        //PlayerCore targetPlayer = PlayerCore.getCore()


        p.setScoreboard(sb);


    }


}
