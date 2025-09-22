package me.manhunt.gui.items.manhunt.world;

import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.game.ManhuntConfiguration;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class SeedItem extends GuiItem {
    private final ManhuntConfiguration game;

    public SeedItem(GuiInventory inventory) {
        super(inventory);

        game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(inventory.getOwner()).getConfiguration();
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.MELON_SEEDS;
    }

    @Override
    public String getName() {
        return ChatColor.LIGHT_PURPLE + "World Seed " + ChatColor.WHITE + "(" + game.getWorldSettings().getSeed() + ")";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Set the seed of the world generation",
                ChatColor.GOLD + "to play Manhunt in."
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
