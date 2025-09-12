package me.manhunt.gui;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public abstract class GuiItemHead extends GuiItem implements GuiPlayerHead {
    public GuiItemHead(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public Material getMaterial() {
        return Material.PLAYER_HEAD;
    }

    @Override
    public ItemStack getItemStack() {
        ItemStack output = new ItemStack(getMaterial());
        ItemMeta meta = Objects.requireNonNull(output.getItemMeta());

        if (getName() != null)
            meta.setDisplayName(getName());

        meta.setLore(getLore());
        meta.addItemFlags(ItemFlag.values());
        meta.addAttributeModifier(Attribute.ARMOR, REMOVE_STATEMENTS);

        if (meta instanceof SkullMeta && getHeadType() != null)
            ((SkullMeta) meta).setOwningPlayer(getHeadType());

        output.setItemMeta(meta);

        return output;
    }
}