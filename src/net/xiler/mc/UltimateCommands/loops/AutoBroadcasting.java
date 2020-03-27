package net.xiler.mc.UltimateCommands.loops;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

//public class AutoBroadcasting extends JavaPlugin {
//
//    private final Main plugin;
//    public AutoBroadcasting(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    @Override
//    public void onEnable() {
//
//    }
//
//}

public class AutoBroadcasting extends BukkitRunnable {
    private final JavaPlugin plugin;

    public AutoBroadcasting(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getLogger().info("UwU");
    }
}