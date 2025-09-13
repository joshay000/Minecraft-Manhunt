package me.manhunt.gui.items.manhunt;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class CreateManhuntItem extends GuiItem {
    public CreateManhuntItem(GuiInventory inventory) {
        super(inventory);
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT_CREATE.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.CRAFTING_TABLE;
    }

    @Override
    public String getName() {
        return ChatColor.AQUA + "Create New Manhunt";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.WHITE + "Create a new manhunt game."
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

            player.closeInventory();

            return;
        }
    }
}
