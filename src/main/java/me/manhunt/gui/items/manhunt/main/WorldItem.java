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

public class WorldItem extends GuiItem {
    public WorldItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.HEART_OF_THE_SEA;
    }

    @Override
    public String getName() {
        return ChatColor.BLUE + "World Settings";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Set the environment and generation",
                ChatColor.GOLD + "settings of the Manhunt world."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(2, 2);
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
