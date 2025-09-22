package me.manhunt.gui.inventories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import me.manhunt.gui.items.ReturnItem;
import org.bukkit.entity.Player;

import java.util.List;

public class ManhuntWorldSettingsTypeGui extends GuiInventory {
    public ManhuntWorldSettingsTypeGui(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Manhunt World Type Settings";
    }

    @Override
    public List<GuiItemBase> getItems() {
        return List.of(
                GuiFactory.makeItem(GuiItemType.WORLD_TYPE_NORMAL_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_TYPE_FLAT_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_TYPE_AMPLIFIED_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_TYPE_LARGE_BIOMES_ITEM, this),
                new ReturnItem(this) {
                    @Override
                    protected GuiInventoryType getReturnGui() {
                        return GuiInventoryType.MANHUNT_WORLD_SETTINGS_INVENTORY;
                    }
                }
        );
    }
}
