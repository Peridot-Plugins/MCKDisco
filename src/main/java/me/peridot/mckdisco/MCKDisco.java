package me.peridot.mckdisco;

import api.peridot.periapi.PeriAPI;
import me.peridot.mckdisco.bstats.bukkit.Metrics;
import me.peridot.mckdisco.commands.DiscoCommand;
import me.peridot.mckdisco.data.Config;
import me.peridot.mckdisco.inventory.InventoryManager;
import me.peridot.mckdisco.listener.PlayerToggleSneakListener;
import me.peridot.mckdisco.scheduler.ColorScheduler;
import me.peridot.mckdisco.scheduler.UpdateArmorScheduler;
import me.peridot.mckdisco.user.UserCache;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCKDisco extends JavaPlugin {

    private Config config;
    private PeriAPI periAPI;
    private InventoryManager inventoryManager;
    private UserCache userCache;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = new Config(this);
        config.loadConfig();

        periAPI = new PeriAPI(this);
        periAPI.init();

        inventoryManager = new InventoryManager(this);

        userCache = new UserCache();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerToggleSneakListener(this), this);

        getCommand("disco").setExecutor(new DiscoCommand(this));

        new UpdateArmorScheduler(this).start();
        new ColorScheduler(this).start();

        new Metrics(this, 7249);
    }

    public Config getConfiguration() {
        return config;
    }

    public PeriAPI getPeriAPI() {
        return periAPI;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public UserCache getUserCache() {
        return userCache;
    }

}
