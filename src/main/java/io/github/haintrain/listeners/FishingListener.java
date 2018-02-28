package io.github.haintrain.fishing.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static io.github.haintrain.fishing.statics.FishingMethods.*;
import static io.github.haintrain.util.Utility.randomRange;


public class FishingListener implements Listener{

    private static final String GUARDIAN_NAME = ChatColor.RED + "River's Vigil";
    private Plugin plugin;

    public FishingListener(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onCatch(PlayerFishEvent event){

        Player player = event.getPlayer();
        Biome biome = player.getLocation().getBlock().getBiome();
        Location hook = event.getHook().getLocation();

        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH){
            ((Item) event.getCaught()).setItemStack(caughtItem(biome, player, hook));
        }
    }

    private ItemStack caughtItem(Biome biome, Player player, Location hook){
        int rand = randomRange(0, 101);
        int luckLvl = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LUCK);

        if(rand < 75 - (0.15 * luckLvl)){
            return caughtFish(biome, player);
        }
        else if(rand < 80 + (2 * luckLvl)){
            return caughtTreasure(biome);
        }
        else if (rand <= 100){
            return caughtJunk(biome);
        }
        else{
            final Guardian spawn = (Guardian) hook.getWorld().spawnEntity(hook, EntityType.GUARDIAN);
            spawn.setCustomName(GUARDIAN_NAME);

            new BukkitRunnable() {
                public void run() {
                    if(!spawn.isDead()) {
                        spawn.remove();
                    }
                }
            }.runTaskLater(plugin, 3600);
            return new ItemStack(Material.AIR);
        }

    }
}