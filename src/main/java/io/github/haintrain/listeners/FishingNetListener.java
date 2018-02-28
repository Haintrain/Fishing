package io.github.haintrain.fishing.listeners;

import io.github.archemedes.customitem.CustomTag;
import io.github.haintrain.fishing.classes.FishingNet;
import io.github.haintrain.fishing.classes.FishingNetBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static io.github.haintrain.fishing.statics.FishingMethods.caughtFish;
import static io.github.haintrain.fishing.statics.FishingMethods.caughtJunk;
import static io.github.haintrain.fishing.statics.FishingMethods.caughtTreasure;
import static io.github.haintrain.util.Utility.randomRange;

public class FishingNetListener implements Listener{

    @EventHandler
    public void onNetPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItemInHand();
        Block block = event.getBlockPlaced();

        if(CustomTag.hasCustomTag(item, "name")){
            if(CustomTag.getTagValue(item, "name").equals("net")) {
                if (getNearbyWater(block) < 10) {
                    player.sendMessage("more water");
                    event.setCancelled(true);
                } else {
                    FishingNetBlock.makeNet(player.getUniqueId(), block.getLocation(), System.currentTimeMillis());
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() != Material.SPONGE) {
            return;
        }

        event.setDropItems(false);
        player.getWorld().dropItem(player.getLocation(), new FishingNet("Net", 3, "A used net").getNet());
    }

    @EventHandler
    public void onBurn(BlockBurnEvent event) {
        Block block = event.getBlock();
        FishingNetBlock net = FishingNetBlock.getNet(block);
        if (net != null) {
            net.remove();
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        for(Block block: event.blockList()) {
            FishingNetBlock net = FishingNetBlock.getNet(block);
            if (net != null) {
                net.remove();
            }
        }
    }

    @EventHandler
    public void onPistonPush(BlockPistonExtendEvent event) {
        Block block = event.getBlock();
        FishingNetBlock net = FishingNetBlock.getNet(block);
        if (net != null) {
            net.remove();
        }
    }

    @EventHandler
    public void onPistonPull(BlockPistonRetractEvent event) {
        Block block = event.getBlock();
        FishingNetBlock net = FishingNetBlock.getNet(block);
        if (net != null) {
            net.remove();
        }
    }

    @EventHandler
    public void onNetCheck(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.hasBlock() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            FishingNetBlock net = FishingNetBlock.getNet(block);
            if (net != null) {
                Long time = System.currentTimeMillis();
                float timeRand = randomRange(0, 50) * Math.round((time - net.getTimeChecked())/900000L);
                net.setTimeChecked(time);
                if(timeRand > 80) {
                    caughtItem(block.getBiome(), player);
                }
            }
        }
    }

    private ItemStack caughtItem(Biome biome, Player player) {
        int rand = randomRange(0, 100);

        if (rand < 75) {
            return caughtFish(biome, player);
        }
        else if (rand < 80) {
            return caughtTreasure(biome);
        }
        else {
            return caughtJunk(biome);
        }
    }

    public int getNearbyWater(Block block){
        int count = 0;
        BlockFace[] values;
        for (int length = (values = BlockFace.values()).length, i = 0; i < length; ++i) {
            BlockFace f = values[i];
            Material m = block.getRelative(f).getType();
            if (m == Material.WATER || m == Material.STATIONARY_WATER) {
                ++count;
            }
        }
        Block[] array;
        for (int length2 = (array = new Block[] { block.getRelative(2, 0, 0), block.getRelative(0, 0, 2), block.getRelative(0, 0, -2), block.getRelative(-2, 0, 0) }).length, j = 0; j < length2; ++j) {
            Block x = array[j];
            if (x.getType() == Material.WATER || x.getType() == Material.STATIONARY_WATER) {
                ++count;
            }
        }
        return count;
    }
}
