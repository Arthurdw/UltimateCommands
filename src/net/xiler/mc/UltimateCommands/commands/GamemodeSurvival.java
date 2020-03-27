package net.xiler.mc.UltimateCommands.commands;

import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Gamemode;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Logger;

public class GamemodeSurvival implements CommandExecutor {

    private final Main plugin;

    public GamemodeSurvival(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("survival").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Boolean state = Gamemode.change(plugin, sender, args, GameMode.SURVIVAL);
        return state;
    }

    private Logger getLogger() {
        return null;
    }
}
