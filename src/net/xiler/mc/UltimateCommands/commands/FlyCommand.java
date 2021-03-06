package net.xiler.mc.UltimateCommands.commands;


import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Chat;
import net.xiler.mc.UltimateCommands.utils.General;
import net.xiler.mc.UltimateCommands.utils.Perms;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final Main plugin;

    public FlyCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!plugin.config.getBoolean("commands.fly.enabled")) return true;

        Player p = General.getPlayer(sender, plugin, "commands.fly.permissions.others", args);
        if (p == null) return true;

        if (sender instanceof ConsoleCommandSender) {
            fly(p, sender);
            return false;
        }

        if (Perms.contains(plugin, (Player) sender, "commands.fly.permissions.self")) fly(p, sender);
        return false;
    }

    private void fly(Player p, CommandSender sender) {
        String state = "enabled";
        if (p.getAllowFlight()) {
            p.setFlying(false);
            p.setAllowFlight(false);
            state = "disabled";
        } else {
            p.setAllowFlight(true);
            p.setFlying(true);
        }
        if (sender != p) sender.sendMessage(Chat.send(plugin, p, "commands.fly.message").replace("{state}", ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("states." + state))));
        p.sendMessage(Chat.send(plugin, p, "commands.fly.targetmsg").replace("{state}", ChatColor.translateAlternateColorCodes('&', plugin.config.getString("states." + state))).replace("{author}", sender.getName()));
    }
}
