package me.manhunt.gui.items.manhunt.main;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class RosterItem extends GuiItem {
    public RosterItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.WRITABLE_BOOK;
    }

    @Override
    public String getName() {
        return ChatColor.BLUE + "Current Roster";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "View or adjust the roster of",
                ChatColor.GOLD + "your Manhunt game."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(1, 2);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            return;
        }
    }
}
