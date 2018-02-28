package io.github.haintrain.fishing.classes;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FishingNetBlock {
    private UUID owner;
    private Location loc;
    private Long timeChecked;
    private static final Map<Location, FishingNetBlock> nets;

    static {
        nets = new HashMap<Location, FishingNetBlock>();
    }

    public FishingNetBlock(UUID owner, Location loc, Long timePlaced){
        this.owner = owner;
        this.loc = loc;
        timeChecked = timePlaced;
    }

    public static FishingNetBlock getNet(Block block){
        Location loc = block.getLocation();
        return FishingNetBlock.nets.get(loc);
    }

    public static Collection<FishingNetBlock> getAllNets(){
        return FishingNetBlock.nets.values();
    }

    public static FishingNetBlock makeNet(UUID owner, Location loc, Long timePlaced){
        FishingNetBlock net = new FishingNetBlock(owner, loc, timePlaced);
        FishingNetBlock.nets.put(loc, net);
        return net;
    }

    public Location getLocation(){
        return this.loc;
    }

    public void remove() {
        FishingNetBlock.nets.remove(this.loc);
    }

    public UUID getOwner(){
        return owner;
    }

    public Long getTimeChecked(){
        return timeChecked;
    }

    public void setTimeChecked(Long timeChecked){
        this.timeChecked = timeChecked;
    }
}
