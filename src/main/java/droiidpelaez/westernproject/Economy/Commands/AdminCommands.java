package droiidpelaez.westernproject.Economy.Commands;

import droiidpelaez.westernproject.Economy.Bank;
import droiidpelaez.westernproject.Economy.Wallet;
import droiidpelaez.westernproject.UtilCore.GlobalUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor
{
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
        {
            if(sender instanceof Player){
                Player p = (Player) sender;
                Bank bank = Bank.getPlayerBank(p);
                //If the user entered /eco -> Show the usage
                if(args.length == 0){
                    p.sendMessage("");
                    p.sendMessage(ChatColor.GRAY+"==========");
                    p.sendMessage(ChatColor.GRAY+ "Usage:\n"+ChatColor.DARK_GREEN+"/eco add {player} {amount}  :"+ChatColor.GRAY+"  Add funds to a player\n" +
                            ChatColor.DARK_GREEN+ "/eco remove {player} {amount}  :  "+ChatColor.GRAY+" removes funds from a player\n" +
                            ChatColor.DARK_GREEN+"/eco reset  :  "+ChatColor.GRAY+"resets the entire economy.");
                    p.sendMessage(ChatColor.GRAY+"==========");
                    return true;
                }
                String add = "add";
                String remove = "remove";
                String reset = "reset";
                // CASE : add -> (add money)
                if(args[0].toLowerCase().compareTo(add) == 0){
                    //Handles the /eco add ____ case : null player input
                    if(args.length != 3){
                        p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_GREEN+"/eco add {Player} {Amount}");
                        return true;
                    }
                    //This saves a player object of the target.
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    //This handles offline players, or misspelled players
                    if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not "+ChatColor.DARK_GREEN+"online."); return true;}

                    Double deposit = GlobalUtils.checkStrToDErrMsg(args[2], target);
                    if(deposit == -1.0){
                        p.sendMessage(ChatColor.GRAY+"Invalid amount."+ChatColor.DARK_GREEN+" Please try again");
                        return true;
                    }
                    bank.addFunds(target, deposit);
                }
                // CASE : remove -> (remove money)
                else if(args[0].toLowerCase().compareTo(remove) == 0){
                    //Error checking
                    if(args.length != 3){
                        p.sendMessage(ChatColor.GRAY+ "Incorrect usage, please try: "+ ChatColor.DARK_GREEN+"/eco remove {Player} {Amount}");
                        return true;
                    }
                    //Checking the player
                    Player target = Bukkit.getServer().getPlayerExact(args[1]);
                    if(target == null){
                        p.sendMessage(ChatColor.GRAY+"This player is not "+ChatColor.DARK_GREEN+"online.");
                        return true;
                    }
                    Double withdrawal = GlobalUtils.checkStrToDErrMsg(args[2], target);
                    //Error checking
                    if(withdrawal == -1.0){
                        p.sendMessage(ChatColor.GRAY+"Invalid amount."+ChatColor.DARK_GREEN+" Please try again");
                        return true;
                    }
                    Double original = bank.getPlayerFunds(target);
                    p.sendMessage("Attempting to withdrawal : $"+(withdrawal)+"...");
                    //Makes sure the player is withdrawing an allowed amount
                    if(original - withdrawal < 0){
                        p.sendMessage(ChatColor.RED+"Attempted to remove too many funds!");
                    }
                    else{
                        bank.removeFunds(target, withdrawal);
                    }
                }
                // CASE : reset -> (resets all banks)
                else if(args[0].toLowerCase().compareTo(reset) == 0){
                    if(args.length == 1){ p.sendMessage();
                        bank.deleteAllAct();
                        p.sendMessage(ChatColor.GRAY+"All accounts "+ ChatColor.DARK_GREEN+"removed.");
                        return true;
                    }
                }
            }
            return true;
        }
    }
