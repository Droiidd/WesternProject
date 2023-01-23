package droiidpelaez.westernproject.CoreUtils;

import droiidpelaez.westernproject.Economy.Utils.Bank;
import droiidpelaez.westernproject.Economy.Utils.Wallet;
import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Teams.Utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardUtils {

    public void loadPlayerPrefixes(Player p){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();

        sb.registerNewTeam("Wanted").setPrefix("Wanted");
        sb.getTeam("Wanted").setColor(ChatColor.RED);
        PlayerCore pCore = PlayerCore.getPlayerCore(p);
        if(pCore.isPlayerWanted()){
            sb.getTeam("Wanted").addPlayer(p);
            //sb.getTeam("Wanted").se

        }



    }
    public void loadWantedScoreboard(PlayerCore pCore, Integer minuteHand, Integer seconds){
        Player p = pCore.getPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        if(!PlayerCore.hasPlayer(p)){
            System.out.println("NO PLAYER CORE --> SCOREBOARD SHIT");
        }
        //for case 5:00 (Double zero)
 //       if(seconds < 10){
//            if(Team.hasTeam(p.getUniqueId().toString())){
//                Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+Team.getTeam(p).getTeamName()+" " + p.getDisplayName());
//                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
//                Score wantedDisplay = objective.getScore( ChatColor.DARK_RED+""+ ChatColor.BOLD+ "< Wanted >   " + ChatColor.RESET+minuteHand+":0"+seconds);
//                Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
//                Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
//                Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
//                Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
//                Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
//                Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));
//
//                wantedDisplay.setScore(10);
//                separatorLine.setScore(9);
//                bountyDisplay.setScore(8);
//                separatorLine2.setScore(7);
//                bankDisplay.setScore(6);
//                walletDisplay.setScore(5);
//                separatorLine3.setScore(4);
//                p.setScoreboard(sb);
//
//
//            }
//            else {
//                Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+p.getDisplayName());
//                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
//                Score wantedDisplay = objective.getScore( ChatColor.DARK_RED+""+ ChatColor.BOLD+ "< Wanted >   " + ChatColor.RESET+minuteHand+":0"+seconds);
//                Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
//                Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
//                Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
//                Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
//                Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
//                Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));
//
//                wantedDisplay.setScore(10);
//                separatorLine.setScore(9);
//                bountyDisplay.setScore(8);
//                separatorLine2.setScore(7);
//                bankDisplay.setScore(6);
//                walletDisplay.setScore(5);
//                separatorLine3.setScore(4);
//                p.setScoreboard(sb);
//            }
//
//        }
        //add else?
        //Title of scoreboard
        if(Team.hasTeam(p.getUniqueId().toString())){
                Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+Team.getTeam(p).getTeamName()+" " + p.getDisplayName());
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                Score wantedDisplay = objective.getScore( ChatColor.DARK_RED+""+ ChatColor.BOLD+ "< Wanted >   " + ChatColor.RESET+minuteHand+":"+seconds);
                Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
                Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
                Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
                Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
                Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
                Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));

                wantedDisplay.setScore(10);
                separatorLine.setScore(9);
                bountyDisplay.setScore(8);
                separatorLine2.setScore(7);
                bankDisplay.setScore(6);
                walletDisplay.setScore(5);
                separatorLine3.setScore(4);
                p.setScoreboard(sb);


        }
        else {
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+p.getDisplayName());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score wantedDisplay = objective.getScore( ChatColor.DARK_RED+""+ ChatColor.BOLD+ "< Wanted >   " + ChatColor.RESET+minuteHand+":"+seconds);
            Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
            Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
            Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));

            wantedDisplay.setScore(10);
            separatorLine.setScore(9);
            bountyDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            p.setScoreboard(sb);
        }

    }


    public void loadPlayerScoreboard(PlayerCore pCore){
        Player p = pCore.getPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        if(!PlayerCore.hasPlayer(p)){
            System.out.println("NO PLAYER CORE --> SCOREBOARD SHIT");
        }
        //Title of scoreboard
        if(Team.hasTeam(p.getUniqueId().toString())){

            if(pCore.isPlayerWanted()){
                Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+Team.getTeam(p).getTeamName()+" " + p.getDisplayName());
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                Score wantedDisplay = objective.getScore( ChatColor.DARK_RED+""+ ChatColor.BOLD+ "< Wanted >");
                Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
                Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
                Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
                Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
                Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
                Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));

                wantedDisplay.setScore(10);
                separatorLine.setScore(9);
                bountyDisplay.setScore(8);
                separatorLine2.setScore(7);
                bankDisplay.setScore(6);
                walletDisplay.setScore(5);
                separatorLine3.setScore(4);
                p.setScoreboard(sb);
            }else{
                Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+Team.getTeam(p).getTeamName()+" " + p.getDisplayName());
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
                Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
                Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
                Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
                Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
                Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));

                separatorLine.setScore(9);
                bountyDisplay.setScore(8);
                separatorLine2.setScore(7);
                bankDisplay.setScore(6);
                walletDisplay.setScore(5);
                separatorLine3.setScore(4);
                p.setScoreboard(sb);
            }

        }

        else if(pCore.isPlayerWanted()){
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+p.getDisplayName());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score wantedDisplay = objective.getScore(ChatColor.BOLD+""+ ChatColor.DARK_RED+ "< Wanted >");
            Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
            Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
            Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));

            wantedDisplay.setScore(10);
            separatorLine.setScore(9);
            bountyDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            p.setScoreboard(sb);
        }
        else{
            Objective objective = sb.registerNewObjective("Western Project", "dummy", ChatColor.GRAY+p.getDisplayName());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score separatorLine = objective.getScore(ChatColor.GRAY+"------------==");
            Score separatorLine2 = objective.getScore(ChatColor.GRAY+"---------==");
            Score separatorLine3 = objective.getScore(ChatColor.GRAY+"------==");
            Score bankDisplay = objective.getScore(ChatColor.DARK_GREEN+"Bank: "+ChatColor.GRAY+ Bank.getPlayerFunds(p).toString()+"g");
            Score walletDisplay = objective.getScore(ChatColor.DARK_GREEN+"Wallet: "+ChatColor.GRAY+ Wallet.getPlayerFunds(p).toString()+"g");
            Score bountyDisplay = objective.getScore(ChatColor.RED+"Bounty: "+ pCore.getPlayerBounty(p));

            separatorLine.setScore(9);
            bountyDisplay.setScore(8);
            separatorLine2.setScore(7);
            bankDisplay.setScore(6);
            walletDisplay.setScore(5);
            separatorLine3.setScore(4);
            //PlayerCore targetPlayer = PlayerCore.getCore()
            p.setScoreboard(sb);
        }



    }


}
