package net.xiler.mc.UltimateCommands.commands;

import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.General;
import net.xiler.mc.UltimateCommands.utils.Perms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    private final Main plugin;

    public StaffChat(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("sc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!plugin.getConfig().getBoolean("commands.staffchat.enabled")) return true;

        if (sender instanceof Player) {
            if (Perms.contains(plugin, (Player) sender, "commands.staffchat.permission")) return General.privateBroadcast(sender, plugin,"commands.staffchat.prefix", "commands.staffchat.permission", args);
        } else if (sender instanceof ConsoleCommandSender) return General.privateBroadcast(sender, plugin,"commands.staffchat.prefix", "commands.staffchat.permission", args);
        else return true;
        return false;
    }
}
