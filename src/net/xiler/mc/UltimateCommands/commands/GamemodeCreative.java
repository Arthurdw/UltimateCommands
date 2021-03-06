package net.xiler.mc.UltimateCommands.commands;

import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Gamemode;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GamemodeCreative implements CommandExecutor {

    private final Main plugin;

    public GamemodeCreative(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("creative").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Boolean state = Gamemode.change(plugin, sender, args, GameMode.CREATIVE);
        return state;
    }
}
