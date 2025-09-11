package me.manhunt.gui.items.pagination;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class PreviousPageItem extends GuiItem {
    public PreviousPageItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public Material getMaterial() {
        return Material.OAK_BUTTON;
    }

    @Override
    public String getName() {
        return ChatColor.GOLD + "Previous Page";
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(2, 4);
    }

    @Override
    public void click(Player player) {
        inventory.previousPage();
    }
}
