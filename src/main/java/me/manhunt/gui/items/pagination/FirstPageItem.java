package me.manhunt.gui.items.pagination;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class FirstPageItem extends GuiItem {
    public FirstPageItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.STONE_BUTTON;
    }

    @Override
    public String getName() {
        return ChatColor.GOLD + "First Page";
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(1, 4);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            player.closeInventory();

            return;
        }

        inventory.firstPage(hasClickPermissions(player));
    }
}
