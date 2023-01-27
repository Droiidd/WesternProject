package droiidpelaez.westernproject.UtilCore;

import droiidpelaez.westernproject.PlayerCore.PlayerCore;
import droiidpelaez.westernproject.Roles.Sheriff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.*;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class GlobalUtils {
    public static void loadPidScoreboard(String pId) {
        Player target = GlobalUtils.getPlayerFromString(pId);
        if (target != null) {
            ScoreboardUtils sb = new ScoreboardUtils();
            sb.loadPlayerScoreboard(target);
        }
        System.out.println("Oops - team add player");
    }

    public static Double checkStrToDErrMsg(String s, Player p) {
        try {
            Double amount = Double.parseDouble(s);
            return amount;
        } catch (NumberFormatException e) {
            p.sendMessage(ChatColor.RED + "Incorrect usage " + ChatColor.GRAY + "please enter a valid amount.");
        }
        return -1.0;
    }

    public static Double StrToDNoMsg(String s, Player p) {
        try {
            Double amount = Double.parseDouble(s);
            return amount;
        } catch (NumberFormatException e) {
            return -1.0;
        }

    }

    public static Double getGoldStrToD(ItemStack goldItem, Player p) {
        String amount = ChatColor.stripColor(goldItem.getItemMeta().getDisplayName());
        String numberOnly = amount.replaceAll("[^0-9]", "");
        Double depositGold = (GlobalUtils.StrToDNoMsg(numberOnly, p)) / 10;
        return depositGold;
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static Player getPlayerFromString(String uuid) {
        UUID playerId = UUID.fromString(uuid);
        Player p = (Player) Bukkit.getOfflinePlayer(playerId);
        if (p == null) {
            System.out.println(ChatColor.RED + "PLAYER NOT FOUNDN NOT FOUND");
            return null;
        }
        return p;
    }

    public static void loadPlayerNameTags(Player p) {
        PlayerCore pCore = PlayerCore.getPlayerCore(p.getUniqueId().toString());
        p.sendMessage("Loading tag...");
        if (Sheriff.isSheriff(p.getUniqueId().toString())) {
            Sheriff sheriff = Sheriff.getSheriff(p.getUniqueId().toString());
            p.setDisplayName(sheriff.getRoleDisplayName()+p.getDisplayName());
        } else if (pCore.isPlayerWanted()){
            p.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wanted "+ChatColor.RED+ p.getDisplayName());
        }
        else{
            p.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Bandit " +p.getDisplayName());
        }
    }

    public static void loadPlayerListName(Player p) {
        PlayerCore pCore= PlayerCore.getPlayerCore(p.getUniqueId().toString());
        if (Sheriff.isSheriff(p.getUniqueId().toString())) {
            Sheriff sheriff = Sheriff.getSheriff(p.getUniqueId().toString());
            p.setPlayerListName(sheriff.getRoleDisplayName() + p.getDisplayName());
        }
        else if (pCore.isPlayerWanted()){
            p.setPlayerListName( ChatColor.DARK_RED+"" +ChatColor.BOLD+"Wanted "+ChatColor.RED +p.getDisplayName());
        }
        else{
            p.setPlayerListName(ChatColor.GRAY + "" + ChatColor.BOLD + "Bandit " + ChatColor.RESET + p.getDisplayName());
        }
    }

    public static void loadPlayerStatsDisplay(Player p) {
        loadPlayerListName(p);
        //loadPlayerNameTags(p);
        ScoreboardUtils sb = new ScoreboardUtils();
        sb.loadPlayerScoreboard(p);


    }
    public static ItemStack getPlayerHead(String player, Double headValue) {

        boolean isNewVersion = Arrays.stream(Material.values())
                .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack item = new ItemStack(type, 1);

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(player);
        meta.setDisplayName(headValue + "g");
        item.setItemMeta(meta);
        return item;
    }


}

