package droiidpelaez.westernproject.Items;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class GeodeController
{
    private ArrayList<ItemStack> geodeContents = new ArrayList<>();
    private MiningItems miningItems = new MiningItems();
    public void randomizeGeodeContents(int odds)
    {
        if(odds % 11 == 0){
            // 1/20
            geodeContents.add(miningItems.getCecilEmerald());
        }else if(odds % 13 == 0){
            // 1/20
            geodeContents.add(miningItems.getRiverDiamond());
        }else if(odds % 9 == 0){
            // 1/10
            geodeContents.add(miningItems.getAmethyst());
        }else if(odds % 2 == 0){
            // 1/2
            geodeContents.add(miningItems.getRubble());
        }else{
            geodeContents.add(miningItems.getSmallBones());
        }
    }
    public ArrayList<ItemStack> openGeode()
    {
        int size = new Random().nextInt(4);
        if(size == 0){
           geodeContents.add(miningItems.getIronScrap());
        }
        else{
            for (int i = 0; i< size; i++){
                int chance = new Random().nextInt(21);
                randomizeGeodeContents(chance);
            }
        }
        return geodeContents;
    }




}
