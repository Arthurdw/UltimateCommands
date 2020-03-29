package net.xiler.mc.UltimateCommands.commands;

import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Perms;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class UCHelp implements CommandExecutor {

    private final Main plugin;

    public UCHelp(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("uc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!plugin.getConfig().getBoolean("commands.help.enabled")) return true;
        if (!(sender instanceof Player)) sendMenu(plugin, sender);
        else if (Perms.contains(plugin, (Player) sender, "commands.help.permission")) sendMenu(plugin, sender);
        return false;
    }

    private static void sendMenu(Plugin plugin, CommandSender sender) {
        for (String msg : plugin.getConfig().getStringList("commands.help.messages")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }
}
