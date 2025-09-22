package me.manhunt.gui.items.manhunt.world;

import me.manhunt.collections.ManhuntPreGameCollection;
import me.manhunt.game.ManhuntConfiguration;
import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItem;
import me.manhunt.singletons.Messages;
import me.manhunt.src.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class WorldTypeItem extends GuiItem {
    private final ManhuntConfiguration game;

    public WorldTypeItem(GuiInventory inventory) {
        super(inventory);

        game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(inventory.getOwner()).getConfiguration();
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.GRASS_BLOCK;
    }

    @Override
    public String getName() {
        WorldType difficulty = game.getWorldSettings().getWorldType();

        String output;

        switch (difficulty) {
            case FLAT -> output = "Superflat";
            case AMPLIFIED -> output = "Amplified";
            case LARGE_BIOMES -> output = "Large Biomes";
            default -> output = "Normal";
        }

        return ChatColor.GREEN + "World Type " + ChatColor.WHITE + "(" + output + ")";
    }

    @Override
    public List<String> getLore() {
        return List.of(
                ChatColor.GOLD + "Choose the type of world",
                ChatColor.GOLD + "to play Manhunt in."
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

            player.closeInventory();

            return;
        }
    }
}
