package net.xiler.mc.UltimateCommands.commands;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Gui;
import net.xiler.mc.UltimateCommands.utils.Chat;
import net.xiler.mc.UltimateCommands.utils.General;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TagsCommand implements CommandExecutor {

    private final Main plugin;
    private FileConfiguration tags;

    public TagsCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tag").setExecutor(this);
        tags = plugin.tagsCFG;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Chat.e(plugin.config.getString("prefix") + "&4You need to be an online player to be able to do this!"));
            return true;
        }
        ArrayList data = new ArrayList();

        for (Object tag : tags.getList("tags")) {
            LinkedHashMap tempData = (LinkedHashMap) ((LinkedHashMap) tag).get(((LinkedHashMap) tag).keySet().toArray()[0]);
            ArrayList lores = new ArrayList();
            lores.addAll((ArrayList) tempData.get("lores"));
            String hasPerms = sender.hasPermission(String.valueOf(tempData.get("permission"))) ? "allowed" : "denied";
            lores.add("");
            lores.add(Chat.e((tags.getString("perms.msg")).replace("{perm}", tags.getString("perms." + hasPerms))));

            String[][] tempArray = {{String.valueOf(tempData.get("material"))},
                    {String.valueOf(tempData.get("amount"))},
                    {String.valueOf(tempData.get("name"))},
                    General.arrayListToStringList(lores)
            };

            data.add(tempArray);
        }

        ((Player) sender).openInventory(Gui.GUI((Player) sender, General.treeDimensionArrayListToStringList(data)));
        return false;
    }
}
