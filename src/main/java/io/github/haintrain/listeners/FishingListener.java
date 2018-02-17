package io.github.haintrain.listeners;

import org.bukkit.block.Biome;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import static io.github.haintrain.statics.FishingMethods.*;
import static io.github.haintrain.util.Utility.randomRange;


public class FishingListener implements Listener{

    @EventHandler
    public void onCatch(PlayerFishEvent event){

        Player player = event.getPlayer();
        Biome biome = event.getHook().getLocation().getBlock().getBiome();

        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH){
            player.sendMessage("Caught Something");
            ((Item) event.getCaught()).setItemStack(caughtItem(biome, player));
        }
        else if(event.getState() == PlayerFishEvent.State.FAILED_ATTEMPT){
            player.sendMessage("Failed to catch anything");
        }

    }

    private ItemStack caughtItem(Biome biome, Player player){
        int rand = randomRange(0, 100);

        if(rand < 75){
            return caughtFish(biome, player);
        }
        else if(rand < 80){
            return caughtTreasure(biome);
        }
        else{
            return caughtJunk(biome);
        }
    }



}