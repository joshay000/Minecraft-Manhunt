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

public class GoalItem extends GuiItem {
    public GoalItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.ENDER_EYE;
    }

    @Override
    public String getName() {
        return ChatColor.YELLOW + "End Goal";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Change the goal for the",
                ChatColor.GOLD + "speedrunner(s) to win."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(4, 2);
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
