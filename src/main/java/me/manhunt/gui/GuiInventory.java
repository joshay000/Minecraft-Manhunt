package me.manhunt.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GuiInventory implements GuiInventoryBase {
    protected final Player owner;
    protected final List<GuiItemBase> items;

    public GuiInventory(Player owner) {
        this.owner = owner;
        this.items = new ArrayList<>();
    }

    @Override
    public Inventory getInventory() {
        items.clear();

        List<GuiItemBase> preMadeItems = this.getItems();

        if (preMadeItems != null && !preMadeItems.isEmpty())
            items.addAll(preMadeItems);

        int rows = getRows();

        Inventory inventory = Bukkit.createInventory(owner, rows * 9, getTitle());

        for (GuiItemBase item : items) {
            Point slot = item.getInventoryLocation();

            int index = slot.x + slot.y * 9;

            inventory.setItem(index, item.getItemStack());
        }

        return inventory;
    }

    public List<GuiItemBase> getStoredItems() {
        return items;
    }

    public Player getOwner() {
        return owner;
    }
}
