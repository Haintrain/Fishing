package io.github.haintrain.classes;

import io.github.archemedes.customitem.CustomTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.haintrain.util.Utility.makeItem;

public class FishingNet{
    private final String name;
    private final List<String> lore;
    private final Integer power;

    public FishingNet(final String name, final Integer power, final String... lore){
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

    public final Integer getPower(){
        return power;
    }

    public final ItemStack getNet(){
        ItemStack net = makeItem(new ItemStack(Material.SPONGE), name, lore);
        final CustomTag netTag = CustomTag.getFrom(net);

        netTag.put("name", this.name);
        netTag.put("item", "net");
        netTag.put("power", power.toString());
        net = CustomTag.applyTo(netTag, net);

        return net;
    }
}
