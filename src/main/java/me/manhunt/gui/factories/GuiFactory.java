package me.manhunt.gui.factories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.inventories.TestInventory;
import me.manhunt.gui.items.pagination.*;
import org.bukkit.entity.Player;

public final class GuiFactory {
    private GuiFactory() {}

    public static GuiInventory makeGui(GuiInventoryType type, Player owner) {
        return switch (type) {
            case TEST_INVENTORY -> new TestInventory(owner);
            default -> throw new RuntimeException("The inventory type provided is not valid or is not implemented yet.");
        };
    }

    public static GuiItemBase makeItem(GuiItemType type, GuiInventory inventory, Object...args) {
        return switch (type) {
            case PAGINATION_FIRST_PAGE -> new FirstPageItem(inventory);
            case PAGINATION_LAST_PAGE -> new LastPageItem(inventory);
            case PAGINATION_NEXT_PAGE -> new NextPageItem(inventory);
            case PAGINATION_PREVIOUS_PAGE -> new PreviousPageItem(inventory);
            case PAGINATION_CURRENT_PAGE -> new CurrentPageItem(inventory, (int) args[0], (int) args[1]);
            default -> throw new RuntimeException("The item type provided is not valid or is not implemented yet.");
        };
    }
}
