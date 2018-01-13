package io.github.haintrain.fishing;

import io.github.archemedes.customitem.CustomTag;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Catching implements Listener{

    private ItemStack[] treasure = new ItemStack[]{
            makeFishingItem(new ItemStack(Material.COOKED_CHICKEN), "Chicken Tofu", "Don't question how it got here"),
            makeFishingItem(new ItemStack(Material.BREAD), "Not Pockets", "It's just bread"),
            makeFishingItem(new ItemStack(Material.BOOK_AND_QUILL), "Notepad", "One wonders how the pages are still dry"),
            makeFishingItem(new ItemStack(Material.DIAMOND), "Pearl", "Lucky you"),
            makeFishingItem(new ItemStack(Material.CHORUS_FRUIT), "Purple Fruit"),
            makeFishingItem(new ItemStack(Material.GOLD_INGOT), "Solid Bar of Gold", "It has a hefty weight to it"),};

    private ItemStack[] junk = new ItemStack[]{
            new ItemStack(Material.ROTTEN_FLESH),
            new ItemStack(Material.ROTTEN_FLESH),
            new ItemStack(Material.BOWL),
            new ItemStack(Material.LEASH),
            new ItemStack(Material.BOOK),
            new ItemStack(Material.STRING),
            new ItemStack(Material.POTION),
            new ItemStack(Material.STICK),
            new ItemStack(Material.NETHER_BRICK),
            new ItemStack(Material.LEATHER),
            new ItemStack(Material.LEATHER_BOOTS),
            new ItemStack(Material.BONE),
            new ItemStack(Material.TRIPWIRE_HOOK),
            new ItemStack(Material.BOW),
            new ItemStack(Material.WOOD_SWORD),
            new ItemStack(Material.WOOD_SPADE),
            new ItemStack(Material.ARROW),
            new ItemStack(Material.LONG_GRASS),
            new ItemStack(Material.REDSTONE_TORCH_OFF),
            new ItemStack(Material.FLOWER_POT_ITEM),
            new ItemStack(Material.FISHING_ROD),
            new ItemStack(Material.BEETROOT_SEEDS),
            new ItemStack(Material.POTATO_ITEM),
            new ItemStack(Material.CARROT_ITEM),
            new ItemStack(Material.MELON_SEEDS),
            new ItemStack(Material.PUMPKIN_SEEDS)};


    @EventHandler
    public void onCatchFish(PlayerFishEvent event){

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

    private ItemStack caughtFish(Biome biome, Player player){

        ItemStack fish = Species.HERRING.getFish();
        int rand;

        switch(biome){
            case BIRCH_FOREST: case RIVER: case PLAINS: case FOREST: case FOREST_HILLS: case BEACHES: case EXTREME_HILLS: case EXTREME_HILLS_WITH_TREES: case JUNGLE: case JUNGLE_EDGE: case JUNGLE_HILLS: case SMALLER_EXTREME_HILLS: case ROOFED_FOREST: case STONE_BEACH:
                rand = randomRange(1, 10);
                switch(rand){
                    case 1: fish = Species.HERRING.getFish(); break;
                    case 2: fish = Species.LAKEFISH.getFish(); break;
                    case 3: fish = Species.CUTTTHROAT.getFish(); break;
                    case 4: fish = Species.CATFISH.getFish();
                            player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1, 1);
                            break;
                    case 5: fish = Species.FLOUNDER.getFish(); break;
                    case 6: fish = Species.MUDSUCKER.getFish(); break;
                    case 7: fish = Species.PERCH.getFish(); break;
                    case 8: fish = Species.WORMFISH.getFish(); break;
                    case 9: fish = Species.CATFISH.getFish(); break;
                    case 10: fish = Species.FLOUNDER.getFish(); break;
                }
            case DESERT: case DESERT_HILLS: case SAVANNA: case SAVANNA_ROCK: case MESA: case MESA_CLEAR_ROCK: case MESA_ROCK:
                rand = randomRange(1, 5);
                switch(rand) {
                    case 1: fish = Species.SNAKEFISH.getFish(); break;
                    case 2: fish = Species.SANDFISH.getFish(); break;
                    case 3: fish = Species.TOUNGUEFISH.getFish(); break;
                    case 4: fish = Species.CACTUSFISH.getFish(); break;
                    case 5: fish = Species.COWFISH.getFish();
                            player.playSound(player.getLocation(), Sound.ENTITY_COW_AMBIENT, 1, 1);
                            break;
                }
            case SWAMPLAND:
                rand = randomRange(1, 5);
                switch (rand){
                    case 1: fish = Species.RIBBONTAIL.getFish(); break;
                    case 2: fish = Species.SPITTAIL.getFish(); break;
                    case 3: fish = Species.STONEFISH.getFish(); break;
                    case 4: fish = Species.GHOSTFISH.getFish(); break;
                    case 5: fish = Species.GHOULFISH.getFish(); break;
                    case 6: fish = Species.HAGFISH.getFish(); break;
                }
            case OCEAN: case DEEP_OCEAN:
                rand = randomRange(1, 7);
                switch(rand){
                    case 1: fish = Species.CARP.getFish(); break;
                    case 2: fish = Species.LONGFIN.getFish(); break;
                    case 3: fish = Species.COD.getFish(); break;
                    case 4: fish = Species.SARDINE.getFish(); break;
                    case 5: fish = Species.BARRELFISH.getFish(); break;
                    case 6: fish = Species.TUNA.getFish(); break;
                    case 7: fish = Species.BASS.getFish(); break;
                }
            case FROZEN_OCEAN: case FROZEN_RIVER: case TAIGA: case TAIGA_COLD: case TAIGA_COLD_HILLS: case TAIGA_HILLS: case REDWOOD_TAIGA: case REDWOOD_TAIGA_HILLS: case COLD_BEACH: case ICE_FLATS: case ICE_MOUNTAINS:
                rand = randomRange(1, 3);
                switch(rand) {
                    case 1: fish = Species.SUNFISH.getFish(); break;
                    case 2: fish = Species.MAGIKARP.getFish(); break;
                    case 3: fish = Species.MOLTENFISH.getFish(); break;
                }
            case VOID: case HELL: case SKY: break;
        }
        return fish;
    }

    private ItemStack caughtTreasure(Biome biome){
        int rand = randomRange(0, treasure.length - 1);

        return treasure[rand];
    }

    private ItemStack caughtJunk(Biome biome){
        int rand = randomRange(0, junk.length - 1);

        return junk[rand];
    }

    private int randomRange(int low, int high)
    {
        Random generator = new Random();
        return generator.nextInt(high - low + 1) + low;
    }

    private ItemStack makeFishingItem(ItemStack item, String name, final String... lore){

        final List<String> lores;

        if (lore != null && lore.length > 0) {
            lores = new ArrayList<String>();
            for (final String s : lore) {
                lores.add(s);
            }
        }
        else {
            lores = null;
        }

        if (name != null || lores != null) {
            final ItemMeta m = item.getItemMeta();
            if (name != null) {
                m.setDisplayName(name);
                final CustomTag itemTag = CustomTag.getFrom(item);

                itemTag.put("name", name);
                item = CustomTag.applyTo(itemTag, item);
            }
            if (lore != null) {
                m.setLore(lores);
            }
            item.setItemMeta(m);
        }
        return item;
    }
}