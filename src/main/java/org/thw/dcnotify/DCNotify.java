package org.thw.dcnotify;

import org.bukkit.plugin.java.JavaPlugin;

public final class DCNotify extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTimeTracker(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
