package io.github.haintrain.fishing.statics;

import io.github.haintrain.fishing.enums.FishingEnum;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static io.github.haintrain.util.Utility.loreToArray;
import static io.github.haintrain.util.Utility.makeItem;
import static io.github.haintrain.util.Utility.randomRange;

public class FishingMethods {


    private static ItemStack[] treasure = new ItemStack[]{
            makeItem(new ItemStack(Material.COOKED_CHICKEN), "Chicken Tofu", "fishing treasure", loreToArray("Don't question how it got here")),
            makeItem(new ItemStack(Material.BREAD), "Not Pockets", "fishing treasure", loreToArray("It's just bread")),
            makeItem(new ItemStack(Material.BOOK_AND_QUILL), "Notepad","fishing treasure", loreToArray("One wonders how the pages are still dry")),
            makeItem(new ItemStack(Material.DIAMOND), "Pearl", "fishing treasure", loreToArray("Lucky you")),
            makeItem(new ItemStack(Material.CHORUS_FRUIT), "Purple Fruit", "fishing treasure", loreToArray("This was probably a mistake")),
            makeItem(new ItemStack(Material.GOLD_INGOT), "Solid Bar of Gold", "fishing treasure", loreToArray("It has a hefty weight to it")),
            FishingEnum.THEHARRISONFISH.getFish(),};

    private static ItemStack[] junk = new ItemStack[]{
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


    public static ItemStack caughtFish(Biome biome, Player player){

        ItemStack fish = FishingEnum.HERRING.getFish();
        int rand;

        switch(biome){
            case BIRCH_FOREST: case RIVER: case PLAINS: case FOREST: case FOREST_HILLS: case BEACHES: case EXTREME_HILLS: case EXTREME_HILLS_WITH_TREES: case JUNGLE: case JUNGLE_EDGE: case JUNGLE_HILLS: case SMALLER_EXTREME_HILLS: case ROOFED_FOREST: case STONE_BEACH:
                rand = randomRange(1, 10);
                switch(rand){
                    case 1: fish = FishingEnum.HERRING.getFish(); break;
                    case 2: fish = FishingEnum.LAKEFISH.getFish(); break;
                    case 3: fish = FishingEnum.CUTTTHROAT.getFish(); break;
                    case 4: fish = FishingEnum.CATFISH.getFish();
                        player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1, 1);
                        break;
                    case 5: fish = FishingEnum.FLOUNDER.getFish(); break;
                    case 6: fish = FishingEnum.MUDSUCKER.getFish(); break;
                    case 7: fish = FishingEnum.PERCH.getFish(); break;
                    case 8: fish = FishingEnum.WORMFISH.getFish(); break;
                    case 9: fish = FishingEnum.CATFISH.getFish(); break;
                    case 10: fish = FishingEnum.FLOUNDER.getFish(); break;
                }
            case DESERT: case DESERT_HILLS: case SAVANNA: case SAVANNA_ROCK: case MESA: case MESA_CLEAR_ROCK: case MESA_ROCK:
                rand = randomRange(1, 6);
                switch(rand) {
                    case 1: fish = FishingEnum.SNAKEFISH.getFish(); break;
                    case 2: fish = FishingEnum.SANDFISH.getFish(); break;
                    case 3: fish = FishingEnum.TOUNGUEFISH.getFish(); break;
                    case 4: fish = FishingEnum.CACTUSFISH.getFish(); break;
                    case 5: fish = FishingEnum.HAGFISH.getFish(); break;
                    case 6: fish = FishingEnum.COWFISH.getFish();
                        player.playSound(player.getLocation(), Sound.ENTITY_COW_AMBIENT, 1, 1);
                        break;
                }
            case SWAMPLAND:
                rand = randomRange(1, 6);
                switch (rand){
                    case 1: fish = FishingEnum.RIBBONTAIL.getFish(); break;
                    case 2: fish = FishingEnum.SPITTAIL.getFish(); break;
                    case 3: fish = FishingEnum.STONEFISH.getFish(); break;
                    case 4: fish = FishingEnum.GHOSTFISH.getFish(); break;
                    case 5: fish = FishingEnum.GHOULFISH.getFish(); break;
                    case 6: fish = FishingEnum.HAGFISH.getFish(); break;
                }
            case OCEAN: case DEEP_OCEAN:
                rand = randomRange(1, 7);
                switch(rand){
                    case 1: fish = FishingEnum.CARP.getFish(); break;
                    case 2: fish = FishingEnum.LONGFIN.getFish(); break;
                    case 3: fish = FishingEnum.COD.getFish(); break;
                    case 4: fish = FishingEnum.SARDINE.getFish(); break;
                    case 5: fish = FishingEnum.BARRELFISH.getFish(); break;
                    case 6: fish = FishingEnum.TUNA.getFish(); break;
                    case 7: fish = FishingEnum.BASS.getFish(); break;
                }
            case FROZEN_OCEAN: case FROZEN_RIVER: case TAIGA: case TAIGA_COLD: case TAIGA_COLD_HILLS: case TAIGA_HILLS: case REDWOOD_TAIGA: case REDWOOD_TAIGA_HILLS: case COLD_BEACH: case ICE_FLATS: case ICE_MOUNTAINS:
                rand = randomRange(1, 3);
                switch(rand) {
                    case 1: fish = FishingEnum.SUNFISH.getFish(); break;
                    case 2: fish = FishingEnum.MAGIKARP.getFish(); break;
                    case 3: fish = FishingEnum.MOLTENFISH.getFish(); break;
                }
            case VOID: case HELL: case SKY: break;
        }
        return fish;
    }

    public static ItemStack caughtTreasure(Biome biome){
        int rand = randomRange(0, treasure.length - 1);

        return treasure[rand];
    }

    public static ItemStack caughtJunk(Biome biome){
        int rand = randomRange(0, junk.length - 1);

        return junk[rand];
    }
}
