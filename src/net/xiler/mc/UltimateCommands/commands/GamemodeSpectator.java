package net.xiler.mc.UltimateCommands.commands;

import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Gamemode;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GamemodeSpectator implements CommandExecutor {

    private final Main plugin;

    public GamemodeSpectator(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("spectator").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Boolean state = Gamemode.change(plugin, sender, args, GameMode.SPECTATOR);
        return state;
    }
}
