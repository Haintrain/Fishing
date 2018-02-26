package io.github.haintrain.fishing;

import io.github.haintrain.fishing.classes.FishingNetBlock;
import io.github.haintrain.SQL.SQL;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.util.Collection;

public class FishingNetSave {
    private static PreparedStatement statement;



    public static void saveNet(){
        final Collection<FishingNetBlock> nets = FishingNetBlock.getAllNets();

        for(FishingNetBlock net: nets) {
            try {
                Location loc = net.getLocation();
                statement = SQL.getConnection().prepareStatement("INSERT INTO fishingNets VALUES (?,?,?,?,?,?)");

                statement.setString(1, loc.getWorld().getName());
                statement.setInt(2, loc.getBlockX());
                statement.setInt(3, loc.getBlockY());
                statement.setInt(4, loc.getBlockZ());
                statement.setString(5, net.getOwner().toString());
                statement.setLong(6, net.getTimeChecked());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
