package me.manhunt.gui.inventories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
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
        return new ArrayList<>();
    }
}
