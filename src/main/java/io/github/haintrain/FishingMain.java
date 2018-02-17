package io.github.haintrain;

import io.github.haintrain.commands.FishingNetCommands;
import io.github.haintrain.listeners.ConsumeListener;
import io.github.haintrain.listeners.FishingListener;
import io.github.haintrain.listeners.FishingNetListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FishingMain extends JavaPlugin implements Listener, CommandExecutor {

    private Plugin plugin;

    private Connection connection;
    private String host, database, username, password;
    private int port;

    @Override
    public void onEnable() {
        plugin = this;

        registerEvents(this, new FishingListener(), new ConsumeListener(), new FishingNetListener());
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
            if (!getDataFolder().exists()) {
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
