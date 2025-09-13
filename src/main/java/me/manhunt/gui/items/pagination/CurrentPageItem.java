package me.manhunt.gui.items.pagination;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class CurrentPageItem extends GuiItem {
    private final int page;
    private final int total;

    public CurrentPageItem(GuiInventory inventory, int page, int total) {
        super(inventory);

        this.page = page;
        this.total = total;
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return true;
    }

    @Override
    public Material getMaterial() {
        return Material.OAK_SIGN;
    }

    @Override
    public String getName() {
        return ChatColor.AQUA + "Page " + page + "/" + total;
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(4, 4);
    }

    @Override
    public void click(Player player) {}
}
