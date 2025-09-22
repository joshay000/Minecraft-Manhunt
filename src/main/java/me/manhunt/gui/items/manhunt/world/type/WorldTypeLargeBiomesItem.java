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

public class WorldTypeLargeBiomesItem extends GuiItem {
    private final ManhuntConfiguration game;

    public WorldTypeLargeBiomesItem(GuiInventory inventory) {
        super(inventory);

        game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(inventory.getOwner()).getConfiguration();
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.SAND;
    }

    @Override
    public String getName() {
        return ChatColor.AQUA + "Large Biomes";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Presents the world as a large biomes",
                ChatColor.GOLD + "world type."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(5, 3);
    }

    @Override
    public void click(Player player) {
        playClick();

        if (!hasClickPermissions(player)) {
            sendNotification(Messages.INSUFFICIENT_PERMISSIONS);

            return;
        }

        sendSettingsUpdate(Messages.WORLD_SETTINGS_UPDATED);

        game.getWorldSettings().setWorldType(WorldType.LARGE_BIOMES);

        GuiInventory gui = GuiCollection.getInstance().add(GuiInventoryType.MANHUNT_WORLD_SETTINGS_INVENTORY, player);

        player.openInventory(gui.getInventory());
    }
}
