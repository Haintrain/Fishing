package io.github.haintrain.SQL;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.sql.PreparedStatement;
import java.util.UUID;

public class FishingNetSave {
    private static PreparedStatement statement;

    public static void saveNet(Block net, Integer power, UUID owner, Long time){
        try{
            Location loc = net.getLocation();
            statement = SQL.getConnection().prepareStatement("INSERT INTO fishingNets VALUES (?,?,?,?,?,?)");

            statement.setString(1, loc.getWorld().getName());
            statement.setInt(2, loc.getBlockX());
            statement.setInt(3, loc.getBlockY());
            statement.setInt(4, loc.getBlockZ());
            statement.setString(5, owner.toString());
            statement.setLong(6, time);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
