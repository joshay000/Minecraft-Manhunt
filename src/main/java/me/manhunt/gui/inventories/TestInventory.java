package me.manhunt.gui.inventories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.factories.GuiFactory;
import me.manhunt.gui.items.pagination.RandomPageableItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestInventory extends GuiInventory {
    public TestInventory(Player owner) {
        super(owner);
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public String getTitle() {
        return "Boof";
    }

    @Override
    public List<GuiItemBase> getItems() {
        List<GuiItemBase> output = new ArrayList<>();

        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 9; x++) {
                output.add(new RandomPageableItem(this, x, y));
            }
        }

        return output;
    }
}
