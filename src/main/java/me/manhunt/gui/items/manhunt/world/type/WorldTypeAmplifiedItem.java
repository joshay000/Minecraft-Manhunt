package me.manhunt.gui.items.manhunt.world.type;

import me.manhunt.collections.GuiCollection;
import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.game.ManhuntConfiguration;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class WorldTypeAmplifiedItem extends GuiItem {
    private final ManhuntConfiguration game;

    public WorldTypeAmplifiedItem(GuiInventory inventory) {
        super(inventory);

        game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(inventory.getOwner()).getConfiguration();
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.SNOW_BLOCK;
    }

    @Override
    public String getName() {
        return ChatColor.RED + "Amplified";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Presents the world as an amplified",
                ChatColor.GOLD + "world type.",
                ChatColor.RED + "WARNING: This may require beefy servers",
                ChatColor.RED + "and PCs to run properly."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(3, 3);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            return;
        }

        sendSettingsUpdate(Messages.WORLD_SETTINGS_UPDATED);

        game.getWorldSettings().setWorldType(WorldType.AMPLIFIED);

        GuiInventory gui = GuiCollection.getInstance().add(GuiInventoryType.MANHUNT_WORLD_SETTINGS_INVENTORY, player);

        player.openInventory(gui.getInventory());
    }
}
