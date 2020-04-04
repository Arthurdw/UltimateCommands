package net.xiler.mc.UltimateCommands.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

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

    public static boolean privateBroadcast(CommandSender sender, Plugin plugin, String prefix,  String perm, String[] args) {
        if (args == null || args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages.missingParam")));
            return true;
        }
        Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(prefix) + sender.getName() + ": " + String.join(" ", args)), plugin.getConfig().getString(perm));
        return false;
    }

    public static String[] arrayListToStringList(ArrayList in) {
        String[] returnValue = new String[in.size()];
        for (int i = 0; i < in.size(); i++) returnValue[i] = (String) in.get(i);
        return returnValue;
    }

    public static String[][][] treeDimensionArrayListToStringList(ArrayList in) {
        String[][][] returnValue = new String[in.size()][][];
        for (int i = 0; i < in.size(); i++) returnValue[i] = ((String[][]) in.get(i));
        return returnValue;
    }
}
