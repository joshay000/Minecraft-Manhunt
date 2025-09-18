package me.manhunt.gui.inventories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ManhuntMainGui extends GuiInventory {
    public ManhuntMainGui(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Manhunt Pre-Game";
    }

    @Override
    public List<GuiItemBase> getItems() {
        return List.of(
                GuiFactory.makeItem(GuiItemType.MAIN_ROSTER_ITEM, this),
                GuiFactory.makeItem(GuiItemType.MAIN_WORLD_ITEM, this),
                GuiFactory.makeItem(GuiItemType.MAIN_GAME_ITEM, this),
                GuiFactory.makeItem(GuiItemType.MAIN_GOAL_ITEM, this),
                GuiFactory.makeItem(GuiItemType.MAIN_SPEEDRUNNER_ITEM, this),
                GuiFactory.makeItem(GuiItemType.MAIN_HUNTER_ITEM, this),
                GuiFactory.makeItem(GuiItemType.MAIN_DISBAND_ITEM, this)
        );
    }
}
