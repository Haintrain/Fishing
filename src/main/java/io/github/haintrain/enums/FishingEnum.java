package io.github.haintrain.enums;

import io.github.archemedes.customitem.CustomTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.haintrain.util.Utility.makeItem;

public enum FishingEnum{
    MAGIKARP("Magikarp"),
    HERRING("Herring"),
    LAKEFISH("Lakefish"),
    PERCH("Perch"),
    CUTTTHROAT("Cutthroat"),
    SUNFISH("Sunfish"),
    WORMFISH("Wormfish"),
    CATFISH("Catfish"),
    FLOUNDER("Flounder"),
    THEHARRISONFISH("The Harrison Fish", "He beat me in a round of PvP due to lag"),
    MUDSUCKER("Mudsucker"),
    HAGFISH("Hagfish"),
    RIBBONTAIL("Ribbontail"),
    GHOSTFISH("Ghostfish", "You can't eat it, if you can't see it"),
    GHOULFISH("Ghoulfish"),
    CACTUSFISH("Cactusfish"),
    MOLTENFISH("Moltenfish"),
    SPITTAIL("Spittail"),
    STONEFISH("Stonefish"),
    CARP("Carp"),
    LONGFIN("Longfin"),
    COD("Cod"),
    SARDINE("Sardine"),
    BARRELFISH("Barrelfish"),
    SNAKEFISH("Snakefish"),
    SANDFISH("Sandfish"),
    TOUNGUEFISH("Tounguefish"),
    COWFISH("Cowfish"),
    TUNA("Tuna", "Seems hefty"),
    BASS("Bass", "Seems hefty");

    private final String name;
    private final List<String> lore;

    FishingEnum(final String name, final String... lore){
        this.name = name;

        if (lore != null && lore.length > 0) {
            this.lore = new ArrayList<String>(Arrays.asList(lore));
        }
        else {
            this.lore = null;
        }
    }

    public final String getName(){
        return name;
    }

    public final ItemStack getFish(){
        ItemStack fish = makeItem(new ItemStack(Material.RAW_FISH), name, lore);
        final CustomTag fishTag = CustomTag.getFrom(fish);

        fishTag.put("name", this.name);
        fishTag.put("item", "fish");
        fishTag.put("raw", "true");
        fish = CustomTag.applyTo(fishTag, fish);

        return fish;
    }

}
