package net.xiler.mc.UltimateCommands.listeners;

import net.xiler.mc.UltimateCommands.Main;
import net.xiler.mc.UltimateCommands.utils.Gui;
import net.xiler.mc.UltimateCommands.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvClickCheck implements Listener {

    private Main plugin;

    public InvClickCheck(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getInventory().getTitle();
        if (title.equals(Chat.e(Gui.title))) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (title.equals(Chat.e(Gui.title))) Gui.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
        }
    }
}