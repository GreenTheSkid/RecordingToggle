package com.green.recordingtoggle.recordingtoggle;

import com.green.recordingtoggle.recordingtoggle.commands.Recording;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Recordingtoggle extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Recording(), this);
        getCommand("Recording").setExecutor(new Recording());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[RecordingToggle]: The plugin is enabled!");
        this.getConfig().options().copyDefaults(true);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[RecordingToggle]: The plugin is disabled!");
    }
}
