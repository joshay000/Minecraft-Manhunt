package me.manhunt.gui.items.pagination;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.GuiPageable;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class RandomPageableItem extends GuiItem implements GuiPageable {
    private int x;
    private int y;

    public RandomPageableItem(GuiInventory inventory, int x, int y) {
        super(inventory);

        this.x = x;
        this.y = y;
    }

    @Override
    public Material getMaterial() {
        return Material.GRASS_BLOCK;
    }

    @Override
    public String getName() {
        return "Item " + (x + y * 9);
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(x, y);
    }

    @Override
    public void click(Player player) {
        player.closeInventory();
    }
}
