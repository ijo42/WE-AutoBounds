package ru.ijo42.autobounds;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class WeAutoBounds extends JavaPlugin {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("boundMaterial", 67);
        config.options().copyDefaults(true);
        saveConfig();
        BoundController controller = new BoundController();
        getCommand("autobound").setExecutor(new AutoBoundCommand(controller, config.getInt("boundMaterial")));
        getServer().getPluginManager().registerEvents(new ClickListener(controller), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
