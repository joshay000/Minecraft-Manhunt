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
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class DifficultyItem extends GuiItem {
    private final ManhuntConfiguration game;

    public DifficultyItem(GuiInventory inventory) {
        super(inventory);

        game = ManhuntPreGameCollection.getInstance().getPlayerGameLeading(inventory.getOwner()).getConfiguration();
    }

    @Override
    public boolean hasClickPermissions(Player player) {
        return Permissions.MANHUNT.hasPermission(player);
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_SWORD;
    }

    @Override
    public String getName() {
        Difficulty difficulty = game.getWorldSettings().getDifficulty();

        String output;

        switch (difficulty) {
            case PEACEFUL -> output = "Peaceful";
            case HARD -> output = "Hard";
            case EASY -> output = "Easy";
            default -> output = "Normal";
        }

        return ChatColor.RED + "Difficulty " + ChatColor.WHITE + "(" + output + ")";
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
        return new Point(5, 2);
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
