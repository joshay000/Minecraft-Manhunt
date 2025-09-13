package me.manhunt.collections;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiInventoryBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.factories.GuiFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;
import java.util.List;

public final class GuiCollection {
    private static final GuiCollection SINGLETON = new GuiCollection();

    public static GuiCollection getInstance() {
        return SINGLETON;
    }

    private final List<GuiInventory> inventories;

    private GuiCollection() {
        inventories = new ArrayList<>();
    }

    public GuiInventory add(GuiInventory gui) {
        if (gui.getOwner() != null)
            removeAll(gui.getOwner());

        inventories.add(gui);

        return gui;
    }

    public GuiInventory add(GuiInventoryType type, Player owner) {
        removeAll(owner);

        GuiInventory output = GuiFactory.makeGui(type, owner);

        inventories.add(output);

        return output;
    }

    public boolean contains(InventoryView view, Player owner) {
        String title = view.getTitle();

        for (GuiInventory item : inventories)
            if (item.getTitle().equalsIgnoreCase(title) && item.getOwner().getUniqueId().equals(owner.getUniqueId()))
                return true;

        return false;
    }

    public void removeAll(Player player) {
        if (player == null)
            return;

        for (int i = inventories.size() - 1; i >= 0; i--) {
            GuiInventory gui = inventories.get(i);

            if (gui.getOwner() == null)
                continue;

            if (gui.getOwner().getUniqueId().equals(player.getUniqueId()))
                inventories.remove(i);
        }
    }

    public void clear() {
        for (GuiInventory inventory : inventories)
            inventory.getOwner().closeInventory();

        inventories.clear();
    }

    public int size() {
        return inventories.size();
    }

    public GuiInventory get(int index) {
        return inventories.get(index);
    }

    public GuiInventory get(Player player) {
        if (player == null)
            return null;

        for (GuiInventory item : inventories)
            if (item.getOwner().getUniqueId().equals(player.getUniqueId()))
                return item;

        return null;
    }
}
