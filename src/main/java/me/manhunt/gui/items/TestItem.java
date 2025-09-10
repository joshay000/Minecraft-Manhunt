package me.manhunt.gui.items;

import me.manhunt.gui.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class TestItem extends GuiItem {
    @Override
    public Material getMaterial() {
        return Material.WITHER_ROSE;
    }

    @Override
    public String getName() {
        return "Epic";
    }

    @Override
    public List<String> getLore() {
        return List.of("This was achieved through much blood, sweat, and tears.", "Treat it well...");
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(4, 1);
    }

    @Override
    public void click(Player player) {
        player.sendMessage(ChatColor.GOLD + "It works!");
        player.closeInventory();
    }
}
