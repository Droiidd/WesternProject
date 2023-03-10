package droiidpelaez.westernproject.NPC.Listeners;

import droiidpelaez.westernproject.NPC.NPCgui;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class NPCInteractions implements Listener
{
    private String geologistName = ChatColor.BLUE+""+ ChatColor.BOLD+"Geologist";
    private String bankerName = ChatColor.GOLD+""+ChatColor.BOLD+"Banker";
    private String conductorName = ChatColor.GRAY+""+ChatColor.BOLD+"Conductor";
    private String armorerName = ChatColor.BLUE+""+ ChatColor.BOLD+"Armorer";
    @EventHandler
    public void rightClickNPC(PlayerInteractEntityEvent e)
    {
        Player p = e.getPlayer();
        NPCgui npcGui = new NPCgui();
        if (e.getRightClicked().getType()== EntityType.VILLAGER) {
            e.setCancelled(true);
            if(e.getRightClicked().getCustomName().compareTo(geologistName)==0){
                Inventory shop = npcGui.getGeologistShop(p);
                p.openInventory(shop);
            }else if(e.getRightClicked().getCustomName().compareTo(bankerName)==0){
                Inventory shop = npcGui.getBankerGui(p);
                p.openInventory(shop);
            }
            else if(e.getRightClicked().getCustomName().compareTo(conductorName) == 0){
                Inventory shop = npcGui.getConductorGui(p);
                p.openInventory(shop);
            }
            else if(e.getRightClicked().getCustomName().compareTo(armorerName) == 0){
                Inventory shop = npcGui.getArmorer(p);
                p.openInventory(shop);
            }


            else{
                p.sendMessage("this might be tough.");
            }

        }
    }




    }





