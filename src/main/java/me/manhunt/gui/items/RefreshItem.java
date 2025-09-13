package me.manhunt.gui.items;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class RefreshItem extends GuiItem {
    private final int x;
    private final int y;

    public RefreshItem(GuiInventory inventory, int x, int y) {
        super(inventory);

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.ENDER_PEARL;
    }

    @Override
    public String getName() {
        return ChatColor.GREEN + "Refresh Page";
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(x, y);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            player.closeInventory();

            return;
        }

        inventory.refresh(hasClickPermissions(player));
    }
}
