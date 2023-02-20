package droiidpelaez.westernproject.UtilCore;

import droiidpelaez.westernproject.Core;
import droiidpelaez.westernproject.Economy.Commands.*;
import droiidpelaez.westernproject.NPC.NPCCommands;
import droiidpelaez.westernproject.PlayerCore.Commands.CoreDisplay;
import droiidpelaez.westernproject.PlayerCore.Commands.Medkit;
import droiidpelaez.westernproject.PlayerCore.Commands.ToggleScoreBoard;
import droiidpelaez.westernproject.Roles.Commands.RoleCommands;
import droiidpelaez.westernproject.Teams.Commands.TeamCommands;
import org.bukkit.ChatColor;

public class CommandRegister
{
    private Core plugin;
    public CommandRegister(Core plugin)
    {
        this.plugin = plugin;
    }
    public void regTeamCommands()
    {
        System.out.println(ChatColor.AQUA+"Registering team commands");
        plugin.getCommand("playerinfo").setExecutor(new CoreDisplay());
        plugin.getCommand("team").setExecutor(new TeamCommands(plugin));
        System.out.println(ChatColor.AQUA+"Team commands registered");
    }

    public void regEconomyCommands()
    {
        System.out.println(ChatColor.GREEN+"Registering banking commands");
        plugin.getCommand("balance").setExecutor(new CheckBalance());
        plugin.getCommand("eco").setExecutor(new AdminCommands());
        plugin.getCommand("giveGold").setExecutor(new GiveGold());
        plugin.getCommand("drop").setExecutor(new DropGold());
        plugin.getCommand("wallet").setExecutor(new CheckWallet());
        plugin.getCommand("withdraw").setExecutor(new Withdraw());
        plugin.getCommand("deposit").setExecutor(new Deposit());
        System.out.println(ChatColor.GREEN+"Banking commands registered");
    }
    public void regMiscCommands()
    {
        System.out.println(ChatColor.LIGHT_PURPLE+"Registering misc commands");
        plugin.getCommand("role").setExecutor(new RoleCommands(plugin));
        plugin.getCommand("toggleplayerinfo").setExecutor(new ToggleScoreBoard());
        plugin.getCommand("medkit").setExecutor(new Medkit());
        plugin.getCommand("npc").setExecutor(new NPCCommands());
        System.out.println(ChatColor.LIGHT_PURPLE+"misc commands registered");
    }

}
