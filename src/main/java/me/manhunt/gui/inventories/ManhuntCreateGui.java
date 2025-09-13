package me.manhunt.gui.inventories;

import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import me.manhunt.gui.items.ReturnItem;
import org.bukkit.entity.Player;

import java.util.List;

public class ManhuntCreateGui extends GuiInventory {
    public ManhuntCreateGui(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Manhunt Create";
    }

    @Override
    public List<GuiItemBase> getItems() {
        ManhuntPreGameCollection preGames = ManhuntPreGameCollection.getInstance();

        if (preGames.isPlayerLeadingGame(owner) || preGames.isPlayerInGame(owner))
            return null;

        return List.of(
                GuiFactory.makeItem(GuiItemType.CREATE_ADD_SPEEDRUNNER_ITEM, this),
                GuiFactory.makeItem(GuiItemType.CREATE_ADD_HUNTER_ITEM, this),
                new ReturnItem(this) {
                    @Override
                    protected GuiInventoryType getReturnGui() {
                        return GuiInventoryType.MANHUNT_INVENTORY;
                    }
                }
        );
    }
}
