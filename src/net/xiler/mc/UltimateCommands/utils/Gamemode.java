package net.xiler.mc.UltimateCommands.utils;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Gamemode {

    public static boolean change(Plugin plugin, CommandSender sender, String[] args, GameMode mode) {
        if (!plugin.getConfig().getBoolean("commands.gamemode.enabled")) return true;

        Player p = General.getPlayer(sender, plugin, "commands.gamemode." + mode.name().toLowerCase() + ".permissions.others", args);
        if (p == null) return true;

        if (!(sender instanceof Player)) {
            Gamemode.set(p, mode, sender, plugin);
            return false;
        }

        if (Perms.contains(plugin, (Player) sender, "commands.gamemode." + mode.name().toLowerCase() + ".permissions.self")) Gamemode.set(p, mode, sender, plugin);
        return false;
    }

    private static void set(Player p, GameMode mode, CommandSender sender, Plugin plugin) {
        if (p.getGameMode() == mode) {
            sender.sendMessage(Chat.send(plugin, p, "commands.gamemode.already").replace("{gamemode.last}", ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("commands.gamemode." + mode.name().toLowerCase() + ".call"))));
        } else {
            GameMode gm = p.getGameMode();
            p.setGameMode(mode);
            if (sender != p) sender.sendMessage(Chat.send(plugin, p, "commands.gamemode.message").replace("{gamemode.last}", ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("commands.gamemode." + gm.name().toLowerCase() + ".call"))).replace("{gamemode.now}", ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("commands.gamemode." + mode.name().toLowerCase() + ".call"))));
            p.sendMessage(Chat.send(plugin, p, "commands.gamemode.targetmsg").replace("{gamemode.last}", ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("commands.gamemode." + gm.name().toLowerCase() + ".call"))).replace("{gamemode.now}", ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("commands.gamemode." + mode.name().toLowerCase() + ".call"))).replace("{author}", sender.getName()));
        }
    }
}
