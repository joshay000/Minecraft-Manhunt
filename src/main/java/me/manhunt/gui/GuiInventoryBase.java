package me.manhunt.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public interface GuiInventoryBase {
    int getRows();
    String getTitle();
    Inventory getInventory();
    List<GuiItemBase> getItems();
    Player getOwner();
}
