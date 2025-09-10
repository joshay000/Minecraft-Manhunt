package me.manhunt.gui;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public abstract class GuiItem implements GuiItemBase {
    @Override
    public ItemStack getItemStack() {
        ItemStack output = new ItemStack(getMaterial());
        ItemMeta meta = Objects.requireNonNull(output.getItemMeta());

        if (getName() != null)
            meta.setDisplayName(getName());

        meta.setLore(getLore());
        meta.addItemFlags(ItemFlag.values());

        output.setItemMeta(meta);

        return output;
    }
}
