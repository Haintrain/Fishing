package io.github.haintrain;

import com.google.common.collect.Lists;
import io.github.haintrain.fishing.classes.FishingNetBlock;
import io.github.haintrain.fishing.listeners.ConsumeListener;
import io.github.haintrain.SQL.SQL;
import io.github.haintrain.fishing.commands.FishingNetCommands;
import io.github.haintrain.fishing.listeners.FishingListener;
import io.github.haintrain.fishing.listeners.FishingNetListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    private Plugin plugin;

    private Connection connection;
    private String host, database, username, password;
    private int port;
    private SQL sql;

    @Override
    public void onEnable() {
        plugin = this;

        registerEvents(this, new FishingListener(plugin), new ConsumeListener(), new FishingNetListener());
        registerCommands(this, new FishingNetCommands());

        createConfig();
        loadConfig();

        try {
            openConnection();
            Statement statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = new SQL(connection);

        loadSQL();
    }

    private void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    private void registerCommands(org.bukkit.plugin.Plugin plugin, CommandExecutor ... executors) {
        Integer i = 0;
        String names[] = new String[]{"net"};

        for (CommandExecutor executor : executors) {
            this.getCommand(names[i]).setExecutor(executor);
            i++;
        }
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
        }
    }

    private void loadSQL(){
        try {
            Statement statement = SQL.getConnection().createStatement();
            List<Location> invalid = Lists.newArrayList();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM fishingNets;");
            long time = System.currentTimeMillis();
            while (resultSet.next()) {
                String w = resultSet.getString(1);
                int x = resultSet.getInt(2);
                int y = resultSet.getInt(3);
                int z = resultSet.getInt(4);
                String owner = resultSet.getString(5);
                Long lastChecked = resultSet.getLong(6);
                World world = Bukkit.getWorld(w);
                Location location = new Location(world, x, y, z);

                if (world == null) {
                    this.getLogger().warning("[Net] World not found for fishing Net: " + w);
                    invalid.add(location);
                } else {
                    final Block block = location.getBlock();
                    if (block.getType() != Material.SPONGE) {
                        this.getLogger().warning("[Net] net has become invalid. removing");
                        invalid.add(location);
                    } else {
                        if (time - lastChecked > 1209600000L) {
                            this.getLogger().warning("[Net] net found was too old. Autobreak+remove.");
                            block.setType(Material.WATER);
                            invalid.add(location);
                        } else {
                            FishingNetBlock.makeNet(UUID.fromString(owner), location, lastChecked);
                        }
                    }
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig(){
        FileConfiguration c = getConfig();

        host = c.getString("host");
        port = c.getInt("port");
        database = c.getString("database");
        username = c.getString("username");
        password = c.getString("pass");

        saveDefaultConfig();
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()){
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
