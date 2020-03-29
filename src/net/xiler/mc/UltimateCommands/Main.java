package net.xiler.mc.UltimateCommands;

import net.xiler.mc.UltimateCommands.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new UCHelp(this);
        new FlyCommand(this);
        new GamemodeSurvival(this);
        new GamemodeAdventure(this);
        new GamemodeCreative(this);
        new GamemodeSpectator(this);
        // new AutoBroadcasting(this);

    }
}
