package net.xiler.mc.UltimateCommands.commands;

import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Chat;
import net.xiler.mc.UltimateCommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BroadcastCommand implements CommandExecutor {

    private final Main plugin;

    public BroadcastCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("broadcast").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (Perms.contains(plugin, (Player) sender, "commands.broadcast.permission")) return broadcast(plugin, sender, args);
            else return true;
        }
        else return broadcast(plugin, sender, args);
    }

    private static boolean broadcast(Plugin plugin, CommandSender sender, String[] args) {
        if (args == null || args.length == 0) {
            sender.sendMessage(Chat.send(plugin, null, "messages.missingParam"));
            return true;
        }
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("commands.broadcast.prefix")) + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
        return false;
    }
}
