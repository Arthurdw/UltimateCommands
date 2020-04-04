package net.xiler.mc.UltimateCommands.utils;

import net.xiler.mc.UltimateCommands.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Gui {

    public static String title;
    private static Integer rows;
    private static Main plugin = Main.getPlugin(Main.class);

    public static void init(String title, Integer rows) {
        Gui.title = title;
        Gui.rows = rows * 9;
    }

    public static Inventory GUI(Player p, String[][][] args) {
        Inventory inv = Bukkit.createInventory(null, Gui.rows, Chat.e(Gui.title));
        Integer slot = 1;
        for (String[][] item: args) createItem(inv, Integer.parseInt(item[0][0]), Integer.parseInt(item[1][0]), slot++, item[2][0], item[3]);
        createItem(inv, plugin.tagsCFG.getInt("reset.material"), plugin.tagsCFG.getInt("reset.amount"), rows, plugin.tagsCFG.getString("reset.name"), plugin.tagsCFG.getStringList("reset.lores").toArray(new String[0]));
        return inv;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (slot >= plugin.tagsCFG.getList("tags").toArray().length && slot != rows - 1) return;
        if (clicked.getItemMeta().getDisplayName().equals(Chat.e(plugin.tagsCFG.getString("reset.name")))) {
            p.setDisplayName(p.getName());
            p.setPlayerListName(p.getName());
            p.sendMessage(Chat.e(plugin.config.getString("prefix") + plugin.tagsCFG.getString("messages.reset")));
            return;
        }
        String selected = String.valueOf(((LinkedHashMap) (plugin.tagsCFG.getList("tags").get(slot))).keySet().toArray()[0]),
                permission = String.valueOf(((LinkedHashMap) (((LinkedHashMap) (plugin.tagsCFG.getList("tags").get(slot))).get(selected))).get("permission"));
        if (p.hasPermission(permission)) {
            String prefix = (Chat.e(plugin.tagsCFG.getString("setup"))).replace("{tag}", clicked.getItemMeta().getDisplayName());
            p.setDisplayName(prefix + p.getName());
            p.setPlayerListName(prefix + p.getName());
            // TODO:
            //  Fix data saving!
            plugin.playersCFG.set("players." + p.getUniqueId() + ".prefix", prefix);
            plugin.saveConfig();
            for (String message : plugin.tagsCFG.getStringList("messages.applied")) p.sendMessage(Chat.e(plugin.config.getString("prefix") + message.replace("{tag}", clicked.getItemMeta().getDisplayName()).replace("{user}", p.getDisplayName())));
            p.closeInventory();
        }
    }

    public static ItemStack createItem(Inventory inv, int material, int amount, int invSlot, String name, String... loreString) {
        ItemStack item;
        List<String> lore = new ArrayList();
        item = new ItemStack(Material.getMaterial(material), amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.e(name));
        for (String s : loreString) lore.add(Chat.e(s));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
        return item;
    }
}
