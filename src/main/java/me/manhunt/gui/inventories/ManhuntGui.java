package me.manhunt.gui.inventories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ManhuntGui extends GuiInventory {
    public ManhuntGui(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Manhunt";
    }

    @Override
    public List<GuiItemBase> getItems() {
        return List.of(
                GuiFactory.makeItem(GuiItemType.CREATE_MANHUNT_ITEM, this)
        );
    }
}
