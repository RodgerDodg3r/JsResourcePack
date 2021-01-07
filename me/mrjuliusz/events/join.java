package me.mrjuliusz.events;

import me.mrjuliusz.jsresurcepack.jsresourcepack;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener {

    private static final jsresourcepack plugin = (jsresourcepack) Bukkit.getPluginManager().getPlugin("JsResourcePack");


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onJoinRequestResourcePack(PlayerJoinEvent event)
    {
        if(plugin.getConfig().getBoolean("join") == true)
        {
            String url = plugin.getConfig().getString("url");
            event.getPlayer().setResourcePack(url);
        }
    }
}
