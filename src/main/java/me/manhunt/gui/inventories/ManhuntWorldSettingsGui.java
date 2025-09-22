package me.manhunt.gui.inventories;

import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import me.manhunt.gui.items.ReturnItem;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ManhuntWorldSettingsGui extends GuiInventory {
    public ManhuntWorldSettingsGui(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Manhunt World Settings";
    }

    @Override
    public List<GuiItemBase> getItems() {
        return List.of(
                GuiFactory.makeItem(GuiItemType.WORLD_WORLD_TYPE_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_GENERATE_STRUCTURES_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_SEED_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_DIFFICULTY_ITEM, this),
                GuiFactory.makeItem(GuiItemType.WORLD_GAME_RULE_ITEM, this),
                new ReturnItem(this) {
                    @Override
                    protected GuiInventoryType getReturnGui() {
                        return GuiInventoryType.MANHUNT_MAIN_INVENTORY;
                    }
                }
        );
    }
}
