package me.manhunt.gui.inventories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import me.manhunt.gui.items.ReturnItem;
import org.bukkit.entity.Player;

import java.util.List;

public class ManhuntWorldSettingsDifficultyGui extends GuiInventory {
    public ManhuntWorldSettingsDifficultyGui(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Manhunt World Difficulty Settings";
    }

    @Override
    public List<GuiItemBase> getItems() {
        return List.of(
                GuiFactory.makeItem(GuiItemType.WORLD_DIFFICULTY_PEACEFUL_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_DIFFICULTY_EASY_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_DIFFICULTY_NORMAL_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_DIFFICULTY_HARD_ITEM, this),
                new ReturnItem(this) {
                    @Override
                    protected GuiInventoryType getReturnGui() {
                        return GuiInventoryType.MANHUNT_WORLD_SETTINGS_INVENTORY;
                    }
                }
        );
    }
}
