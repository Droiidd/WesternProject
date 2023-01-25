package droiidpelaez.westernproject.UtilCore;

import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Roles.Sheriff;
import droiidpelaez.westernproject.Teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.checkerframework.checker.units.qual.C;

public class ScoreboardUtils {

    //    public void loadPlayerPrefixes(Player p){
//        ScoreboardManager manager = Bukkit.getScoreboardManager();
//        Scoreboard sb = manager.getNewScoreboard();
//
//        sb.registerNewTeam("Wanted").setPrefix("Wanted");
//        sb.getTeam("Wanted").setColor(ChatColor.RED);
//        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
//        if(pCore.isPlayerWanted()){
//            sb.getTeam("Wanted").addPlayer(p);
//            //sb.getTeam("Wanted").se
//        }
//    }
    public void loadPlayerScoreboard(Player p) {
        if (Sheriff.isSheriff(p.getUniqueId().toString())) {
            loadSheriffScoreboard(p);
        } else {
            loadBanditScoreboard(p);
        }
    }

    public void loadWantedScoreboard(Player p, Integer minuteHand, Integer seconds) {
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        if (!PlayerCore.hasPlayer(p.getUniqueId().toString())) {
            System.out.println("NO PLAYER CORE --> SCOREBOARD SHIT");
        }
        //for case 5:00 (Double zero)
        //Title of scoreboard
        if (Team.hasTeam(p.getUniqueId().toString())) {
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.DARK_RED+""+ChatColor.BOLD+"Wanted");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score wantedDisplay = objective.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "< Wanted >  " + ChatColor.RESET + minuteHand + ":" + seconds);
            Score separatorLine = objective.getScore(ChatColor.GRAY + "------------==");
            Score separatorLine2 = objective.getScore(ChatColor.GRAY + "---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY + "------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN + "Bank: " + ChatColor.GRAY + Bank.getPlayerFunds(p).toString() + "g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN + "Wallet: " + ChatColor.GRAY + Wallet.getPlayerFunds(p).toString() + "g");
            Score banditDisplay = objective.getScore(ChatColor.GRAY + Team.getTeam(p).getTeamName() + " " + ChatColor.RESET + p.getDisplayName());
            Score bountyDisplay = objective.getScore(ChatColor.RED + "Bounty: " + pCore.getPlayerBounty());

            wantedDisplay.setScore(3);
            separatorLine.setScore(10);
            banditDisplay.setScore(9);
            bountyDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            p.setScoreboard(sb);
        } else {
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.DARK_RED+""+ChatColor.BOLD+"Wanted");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score wantedDisplay = objective.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "< Wanted >   " + ChatColor.RESET + minuteHand + ":" + seconds);
            Score separatorLine = objective.getScore(ChatColor.GRAY + "------------==");
            Score separatorLine2 = objective.getScore(ChatColor.GRAY + "---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY + "------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN + "Bank: " + ChatColor.GRAY + Bank.getPlayerFunds(p).toString() + "g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN + "Wallet: " + ChatColor.GRAY + Wallet.getPlayerFunds(p).toString() + "g");
            Score bountyDisplay = objective.getScore(ChatColor.RED + "Bounty: " + pCore.getPlayerBounty());
            Score banditDisplay = objective.getScore(ChatColor.GRAY + Team.getTeam(p).getTeamName() + " " + ChatColor.RESET + p.getDisplayName());

            wantedDisplay.setScore(3);
            separatorLine.setScore(10);
            banditDisplay.setScore(9);
            bountyDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            p.setScoreboard(sb);
        }
    }


    public void loadBanditScoreboard(Player p) {
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        if (!PlayerCore.hasPlayer(p.getUniqueId().toString())) {
            System.out.println("NO PLAYER CORE --> SCOREBOARD SHIT");
        }
        //Title of scoreboard
        if (Team.hasTeam(p.getUniqueId().toString())) {

            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY + "" + ChatColor.BOLD + "Bandit");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score separatorLine = objective.getScore(ChatColor.GRAY + "------------==");
            Score banditDisplay = objective.getScore(ChatColor.GRAY + Team.getTeam(p).getTeamName() + " " + ChatColor.RESET + p.getDisplayName());
            Score separatorLine2 = objective.getScore(ChatColor.GRAY + "---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY + "------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN + "Bank: " + ChatColor.GRAY + Bank.getPlayerFunds(p).toString() + "g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN + "Wallet: " + ChatColor.GRAY + Wallet.getPlayerFunds(p).toString() + "g");
            Score bountyDisplay = objective.getScore(ChatColor.RED + "Bounty: " + pCore.getPlayerBounty());

            separatorLine.setScore(10);
            banditDisplay.setScore(9);
            bountyDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            p.setScoreboard(sb);
        } else {
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY + "" + ChatColor.BOLD + "Bandit");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score separatorLine = objective.getScore(ChatColor.GRAY + "------------==");
            Score banditDisplay = objective.getScore(p.getDisplayName());
            Score separatorLine2 = objective.getScore(ChatColor.GRAY + "---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY + "------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN + "Bank: " + ChatColor.GRAY + Bank.getPlayerFunds(p).toString() + "g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN + "Wallet: " + ChatColor.GRAY + Wallet.getPlayerFunds(p).toString() + "g");
            Score bountyDisplay = objective.getScore(ChatColor.RED + "Bounty: " + pCore.getPlayerBounty());


            separatorLine.setScore(10);
            banditDisplay.setScore(9);
            bountyDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            //PlayerCore targetPlayer = PlayerCore.getCore()
            p.setScoreboard(sb);
        }
    }

    public void loadSheriffScoreboard(Player p) {
//        if(!PlayerCore.hasPlayer(p.getUniqueId().toString())){
//            System.out.println("NO PLAYER CORE --> SCOREBOARD SHIT");
//        }
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        Sheriff sheriff = Sheriff.getSheriff(p.getUniqueId().toString());
        //Title of scoreboard
        if (Team.hasTeam(p.getUniqueId().toString())) {

            Objective objective = sb.registerNewObjective("Western Project", "dummy",ChatColor.GOLD + "" + ChatColor.BOLD + "Sheriff " + ChatColor.RESET );
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score separatorLine = objective.getScore(ChatColor.GRAY + "------------==");
            Score separatorLine2 = objective.getScore(ChatColor.GRAY + "---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY + "------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN + "Bank: " + ChatColor.GRAY + Bank.getPlayerFunds(p).toString() + "g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN + "Wallet: " + ChatColor.GRAY + Wallet.getPlayerFunds(p).toString() + "g");
            Score sheriffDisplay = objective.getScore(ChatColor.GRAY + Team.getTeam(p).getTeamName() + " " + ChatColor.RESET + p.getDisplayName());

            separatorLine.setScore(9);
            sheriffDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            p.setScoreboard(sb);
        } else {
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GOLD + "" + ChatColor.BOLD + "Sheriff " + ChatColor.RESET);
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score separatorLine = objective.getScore(ChatColor.GRAY + "------------==");
            Score separatorLine2 = objective.getScore(ChatColor.GRAY + "---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY + "------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN + "Bank: " + ChatColor.GRAY + Bank.getPlayerFunds(p).toString() + "g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN + "Wallet: " + ChatColor.GRAY + Wallet.getPlayerFunds(p).toString() + "g");
            Score sheriffDisplay = objective.getScore( p.getDisplayName());

            separatorLine.setScore(9);
            sheriffDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            //PlayerCore targetPlayer = PlayerCore.getCore()
            p.setScoreboard(sb);
        }

    }


}
