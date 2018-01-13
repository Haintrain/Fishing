package io.github.haintrain.fishing;

import io.github.archemedes.customitem.CustomTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Species{
    MAGIKARP("Magikarp"),
    HERRING("Herring"),
    LAKEFISH("Lakefish"),
    PERCH("Perch"),
    CUTTTHROAT("Cutthroat"),
    SUNFISH("Sunfish"),
    WORMFISH("Wormfish"),
    CATFISH("Catfish"),
    FLOUNDER("Flounder"),
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

    Species(final String name, final String... lore){
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
        ItemStack fish = new ItemStack((Material.RAW_FISH));
        if (this.name != null || this.lore != null) {
            final ItemMeta m = fish.getItemMeta();
            if (this.name != null) {
                m.setDisplayName(this.name);
            }
            if (this.lore != null) {
                m.setLore(this.lore);
            }
            fish.setItemMeta(m);
        }

        final CustomTag fishTag = CustomTag.getFrom(fish);

        fishTag.put("name", this.name);
        fishTag.put("raw", "true");
        fish = CustomTag.applyTo(fishTag, fish);

        return fish;
    }

}
