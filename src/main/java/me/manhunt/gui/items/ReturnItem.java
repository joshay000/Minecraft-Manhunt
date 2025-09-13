package me.manhunt.gui.items;

import me.manhunt.collections.GuiCollection;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public abstract class ReturnItem extends GuiItem {
    public ReturnItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.DARK_OAK_DOOR;
    }

    @Override
    public String getName() {
        return ChatColor.LIGHT_PURPLE + "Return";
    }

    @Override
    public List<String> getLore() {
        return List.of(ChatColor.GOLD + "Returns to the previous Manhunt menu.");
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(0, inventory.getRows() - 1);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            player.closeInventory();

            return;
        }

        GuiInventory gui = GuiCollection.getInstance().add(getReturnGui(), player);

        player.openInventory(gui.getInventory());
    }

    protected abstract GuiInventoryType getReturnGui();
}
