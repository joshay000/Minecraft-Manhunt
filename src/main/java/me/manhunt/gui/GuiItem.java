package me.manhunt.gui;

import me.manhunt.singletons.SoundPlayer;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;
import java.util.UUID;

public abstract class GuiItem implements GuiItemBase {
    protected static final AttributeModifier REMOVE_STATEMENTS = new AttributeModifier(
            Objects.requireNonNull(NamespacedKey.fromString(UUID.randomUUID().toString())),
            0.0,
            AttributeModifier.Operation.ADD_SCALAR,
            EquipmentSlotGroup.MAINHAND
    );

    protected final GuiInventory inventory;

    public GuiItem(GuiInventory inventory) {
        this.inventory = inventory;
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

        output.setItemMeta(meta);

        return output;
    }

    public GuiInventory getInventory() {
        return inventory;
    }

    protected void playClick() {
        SoundPlayer.getInstance().playGUIClick(inventory.getOwner());
    }

    protected void sendNotification(String message) {
        sendNotification(inventory.getOwner(), message);
    }

    protected void sendNotification(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);

        SoundPlayer.getInstance().playNotification(player);
    }

    protected void sendSettingsUpdate(String message) {
        sendSettingsUpdate(inventory.getOwner(), message);
    }

    protected void sendSettingsUpdate(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);

        SoundPlayer.getInstance().playSettingsUpdate(player);
    }
}
