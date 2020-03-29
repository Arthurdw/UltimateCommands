package net.xiler.mc.UltimateCommands.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class General {

    public static Player getPlayer(CommandSender sender, Plugin plugin, String perm, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length != 0) {
                if (Perms.contains(plugin, p, perm)) {
                    if (Bukkit.getPlayer(args[0]) != null) p = Bukkit.getPlayer(args[0]);
                    else {
                        p.sendMessage(Chat.send(plugin, p, "messages.unknownPlayer").replace("{arg}", args[0]));
                        return null;
                    }
                }
            }
            return p;
        }
        if (args == null || args.length == 0) {
            sender.sendMessage(Chat.send(plugin, null, "messages.missingParam"));
            return null;
        } else  {
            if (Bukkit.getPlayer(args[0]) != null) return Bukkit.getPlayer(args[0]);
            else {
                sender.sendMessage(Chat.send(plugin, null, "messages.unknownPlayer").replace("{arg}", args[0]));
                return null;
            }
        }
    }
}
