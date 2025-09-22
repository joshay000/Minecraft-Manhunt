package me.manhunt.gui.items.manhunt.world;

import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.game.ManhuntConfiguration;
import me.manhunt.game.ManhuntPreGame;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class GenerateStructuresItem extends GuiItem {
    private final ManhuntConfiguration game;

    public GenerateStructuresItem(GuiInventory inventory) {
        super(inventory);

        game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(inventory.getOwner()).getConfiguration();
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.STONE_BRICKS;
    }

    @Override
    public String getName() {
        return ChatColor.YELLOW + "Generate Structures " + ChatColor.WHITE + "(" + (game.getWorldSettings().hasGenerateStructures() ? "Enabled" : "Disabled") + ")";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Allow structures to naturally",
                ChatColor.GOLD + "generate within the Manhunt worlds.",
                ChatColor.RED + "WARNING: This will not generate",
                ChatColor.RED + "strongholds, nether fortresses, etc.",
                ChatColor.RED + "Make sure to change end goals appropriately."
        );
    }

    @Override
    public Point getInventoryLocation() {
        return new Point(3, 2);
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
