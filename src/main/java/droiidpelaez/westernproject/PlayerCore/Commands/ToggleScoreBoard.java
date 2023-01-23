package droiidpelaez.westernproject.PlayerCore.Commands;

import droiidpelaez.westernproject.CoreUtils.ScoreboardUtils;
import droiidpelaez.westernproject.Economy.Utils.Bank;
import droiidpelaez.westernproject.Economy.Utils.Wallet;
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

            //Title of scoreboard
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(PlayerCore.getPlayerCore(p));
            //PlayerCore targetPlayer = PlayerCore.getCore()
        }




        return true;
    }
}
