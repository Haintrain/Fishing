package io.github.haintrain.listeners;

import io.github.haintrain.SQL.FishingNetSave;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import static io.github.archemedes.customitem.CustomTag.isCustomItem;


public class FishingNetListener implements Listener{

    @EventHandler
    public void onNetPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItemInHand();
        Block block = event.getBlockPlaced();

        if(isCustomItem(item)){
            if(getNearbyWater(block) < 10){
                player.sendMessage("more water");
                event.setCancelled(true);
            }
            else{
                FishingNetSave.saveNet(event.getBlockPlaced(), 1, player.getUniqueId(), System.currentTimeMillis());
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.SPONGE) {
            return;
        }

    }

    @EventHandler
    public void onBurn(BlockBurnEvent event) {

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

    }

    @EventHandler
    public void onPistonPush(BlockPistonExtendEvent event) {

    }

    @EventHandler
    public void onPistonPull(BlockPistonRetractEvent event) {

    }


    public int getNearbyWater(Block block){
        int count = 0;
        BlockFace[] values;
        for (int length = (values = BlockFace.values()).length, i = 0; i < length; ++i) {
            final BlockFace f = values[i];
            final Material m = block.getRelative(f).getType();
            if (m == Material.WATER || m == Material.STATIONARY_WATER) {
                ++count;
            }
        }
        Block[] array;
        for (int length2 = (array = new Block[] { block.getRelative(2, 0, 0), block.getRelative(0, 0, 2), block.getRelative(0, 0, -2), block.getRelative(-2, 0, 0) }).length, j = 0; j < length2; ++j) {
            final Block x = array[j];
            if (x.getType() == Material.WATER || x.getType() == Material.STATIONARY_WATER) {
                ++count;
            }
        }
        return count;
    }
}
