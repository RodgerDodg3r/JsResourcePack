package me.mrjuliusz.jsresurcepack;

import me.mrjuliusz.commands.commands;
import me.mrjuliusz.events.join;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;




public final class jsresourcepack extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().addDefault("url", " ");
        getConfig().addDefault("join", true);
        getConfig().options().copyDefaults(true);
        saveConfig();


        Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&',"&4&lJs&f&lResourcePack &e→ &fZaladowano poprawnie."),"*");
        this.getCommand("txt").setExecutor(new commands());
        getServer().getPluginManager().registerEvents(new join(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
