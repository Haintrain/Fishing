package io.github.haintrain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FishingNetCommands implements CommandExecutor{

    public FishingNetCommands(){

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){
        if(!(sender instanceof Player)){
            sender.sendMessage("This command can only be used by players");
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage("test");

        return true;
    }
}
