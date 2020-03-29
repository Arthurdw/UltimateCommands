package net.xiler.mc.UltimateCommands;

import net.xiler.mc.UltimateCommands.commands.*;
import net.xiler.mc.UltimateCommands.misc.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new UCHelp(this);
        new FlyCommand(this);
        new GamemodeSurvival(this);
        new GamemodeAdventure(this);
        new GamemodeCreative(this);
        new GamemodeSpectator(this);
        // new AutoBroadcasting(this);

        int pluginId = 6911;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.MultiLineChart("players_and_servers", () -> {
            Map<String, Integer> valueMap = new HashMap<>();
            valueMap.put("servers", 1);
            valueMap.put("players", Bukkit.getOnlinePlayers().size());
            return valueMap;
        }));
        metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String javaVersion = System.getProperty("java.version");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(javaVersion, 1);
            if (javaVersion.startsWith("1.7")) {
                map.put("Java 1.7", entry);
            } else if (javaVersion.startsWith("1.8")) {
                map.put("Java 1.8", entry);
            } else if (javaVersion.startsWith("1.9")) {
                map.put("Java 1.9", entry);
            } else {
                map.put("Other", entry);
            }
            return map;
        }));
    }
}
