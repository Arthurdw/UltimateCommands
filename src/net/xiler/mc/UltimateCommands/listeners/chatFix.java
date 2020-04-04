package net.xiler.mc.UltimateCommands.listeners;

import net.xiler.mc.UltimateCommands.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class chatFix implements Listener {

    private Main plugin;

    public chatFix(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String prefix = plugin.playersCFG.getString("players." + p.getUniqueId() + ".prefix");
        if (prefix != null) {
            p.setDisplayName(prefix + p.getName());
            p.setPlayerListName(prefix + p.getName());
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setFormat("%s: %s");
    }
}