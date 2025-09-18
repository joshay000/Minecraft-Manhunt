package me.manhunt.gui.factories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.inventories.ManhuntCreateGui;
import me.manhunt.gui.inventories.ManhuntGui;
import me.manhunt.gui.inventories.ManhuntMainGui;
import me.manhunt.gui.items.manhunt.CreateManhuntItem;
import me.manhunt.gui.items.manhunt.create.CreateHunterItem;
import me.manhunt.gui.items.manhunt.create.CreateSpeedrunnerItem;
import me.manhunt.gui.items.pagination.*;
import org.bukkit.entity.Player;

public final class GuiFactory {
    private GuiFactory() {}

    public static GuiInventory makeGui(GuiInventoryType type, Player owner) {
        return switch (type) {
            case MANHUNT_INVENTORY -> new ManhuntGui(owner);
            case MANHUNT_CREATE_INVENTORY -> new ManhuntCreateGui(owner);
            case MANHUNT_MAIN_INVENTORY -> new ManhuntMainGui(owner);
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
            case CREATE_MANHUNT_ITEM -> new CreateManhuntItem(inventory);
            case CREATE_ADD_SPEEDRUNNER_ITEM -> new CreateSpeedrunnerItem(inventory);
            case CREATE_ADD_HUNTER_ITEM -> new CreateHunterItem(inventory);
            default -> throw new RuntimeException("The item type provided is not valid or is not implemented yet.");
        };
    }
}
