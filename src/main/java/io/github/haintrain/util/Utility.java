package io.github.haintrain.util;

import io.github.archemedes.customitem.CustomTag;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility {

    public static int randomRange(int low, int high)
    {
        Random generator = new Random();
        return generator.nextInt(high - low + 1) + low;
    }

    public static List<String> loreToArray(final String... lore){
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

        return lores;
    }

    public static ItemStack makeItem(ItemStack item, String name, final List<String> lore){
        if (name != null || lore != null) {
            final ItemMeta m = item.getItemMeta();
            if (name != null) {
                m.setDisplayName(name);
                final CustomTag itemTag = CustomTag.getFrom(item);

                itemTag.put("name", name);
                item = CustomTag.applyTo(itemTag, item);
            }
            if (lore != null) {
                m.setLore(lore);
            }
            item.setItemMeta(m);
        }
        return item;
    }
}