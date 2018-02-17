package io.github.haintrain.enums;

import io.github.archemedes.customitem.CustomTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.haintrain.util.Utility.makeItem;

public enum FishingNetEnum {
    SUPERNET("Super Net", 10),
    GOODNET("Good Net", 5),
    OKNET("Okay Net", 3),
    POORNET("Poor Net", 1);

    private final String name;
    private final List<String> lore;
    private final Integer power;

    FishingNetEnum(final String name, final Integer power,  final String... lore){
        this.name = name;
        this.power = power;

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
        ItemStack net = makeItem(new ItemStack(Material.WOOD), name, lore);
        final CustomTag netTag = CustomTag.getFrom(net);

        netTag.put("name", this.name);
        netTag.put("raw", "true");
        net = CustomTag.applyTo(netTag, net);

        return net;
    }
}
