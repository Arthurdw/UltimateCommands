package net.xiler.mc.UltimateCommands.loops;

import net.xiler.mc.UltimateCommands.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import java.util.TimerTask;

public class AutoBroadcaster extends TimerTask {

    private int selected = 0;

    private final Main plugin;

    public AutoBroadcaster(Main plugin) {
        this.plugin = plugin;
    }

    public void run() {
        String[] msg = plugin.getConfig().getStringList("autobroadcaster.messages." + ++selected + ".message").toArray(new String[0]);
        if (msg.length == 0) {
            selected = 1;
            msg = plugin.getConfig().getStringList("autobroadcaster.messages.1.message").toArray(new String[0]);
        }
        for (String item : msg) Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', item));
    }
}