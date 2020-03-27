package net.xiler.mc.UltimateCommands.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Perms {

    public static Boolean contains(Plugin pl, Player p, String perm) {
        if (p.hasPermission(pl.getConfig().getString(perm))) return true;
        else p.sendMessage(Chat.send(pl, p, "messages.noPerms"));
        return false;
    }

    public static Boolean noConsole(Plugin pl, CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Chat.send(pl, null, "messages.unableToExecute"));
            return false;
        }
        return true;
    }
}
