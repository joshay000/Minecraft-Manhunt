package me.manhunt.gui.factories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.inventories.TestInventory;
import me.manhunt.gui.items.TestItem;
import org.bukkit.entity.Player;

public final class GuiFactory {
    private GuiFactory() {}

    public static GuiInventory makeGui(GuiInventoryType type, Player owner) {
        return switch (type) {
            case TEST_INVENTORY -> new TestInventory(owner);
            default -> throw new RuntimeException("The inventory type provided is not valid or is not implemented yet.");
        };
    }

    public static GuiItemBase makeItem(GuiItemType type, GuiInventory inventory) {
        return switch (type) {
            case TEST_ITEM -> new TestItem();
            default -> throw new RuntimeException("The item type provided is not valid or is not implemented yet.");
        };
    }
}
