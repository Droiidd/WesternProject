package droiidpelaez.westernproject.PlayerCore.Commands;

import droiidpelaez.westernproject.UtilCore.ScoreboardUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleScoreBoard implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player){
            Player p = (Player) sender;

            //Title of scoreboard
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(p);
            //PlayerCore targetPlayer = PlayerCore.getCore()
        }
        return true;
    }
}
