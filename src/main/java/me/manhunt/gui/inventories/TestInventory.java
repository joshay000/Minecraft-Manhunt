package me.manhunt.gui.inventories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestInventory extends GuiInventory {
    public TestInventory(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 3;
    }

    @Override
    public String getTitle() {
        return "Boof";
    }

    @Override
    public List<GuiItemBase> getItems() {
        List<GuiItemBase> output = new ArrayList<>();

        output.add(GuiFactory.makeItem(GuiItemType.TEST_ITEM, this));

        return output;
    }
}
