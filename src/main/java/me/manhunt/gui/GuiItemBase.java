package me.manhunt.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.List;

public interface GuiItemBase {
    Material getMaterial();
    String getName();
    List<String> getLore();
    ItemStack getItemStack();
    Point getInventoryLocation();
    void click(Player player);
}
